package com.asozial;

import com.asozial.model.FavoriteCat;
import com.asozial.model.Post;
import com.asozial.model.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@QuarkusMain
public class Main {

    public static void main(String... args) {
        Quarkus.run(Startup.class, args);
    }

    public static class Startup implements QuarkusApplication {

        @Inject
        MongoClient mongoClient;

        @Override
        public int run(String... args) throws Exception {
            // DROP ALL COLLECTIONS
            getDatabase().getCollection("user").drop();
            getDatabase().getCollection("post").drop();

            // ADD DATA TO COLLECTIONS
            getDatabase().getCollection("user", User.class).insertMany(createUsers());
            getDatabase().getCollection("post", Post.class).insertMany(createPosts(getDatabase().getCollection("user", User.class).find()));

            Quarkus.waitForExit();
            return 0;
        }

        public List<FavoriteCat> createCats() {
            List<FavoriteCat> cats = new LinkedList<>();

            FavoriteCat cat1 = new FavoriteCat();
            cat1.imgURL = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg";
            cat1.name = "Abyssinian";
            cats.add(cat1);

            FavoriteCat cat2 = new FavoriteCat();
            cat2.imgURL = "https://cdn2.thecatapi.com/images/hj7Oh-SRY.jpg";
            cat2.name = "Burmese";
            cats.add(cat2);

            FavoriteCat cat3 = new FavoriteCat();
            cat3.imgURL = "https://cdn2.thecatapi.com/images/Br8qCwbS9.jpg";
            cat3.name = "Sphynx";
            cats.add(cat3);

            FavoriteCat cat4 = new FavoriteCat();
            cat4.imgURL = "https://cdn2.thecatapi.com/images/AYfFc4vOB.jpg";
            cat4.name = "Persian";
            cats.add(cat4);

            return cats;
        }

        public List<User> createUsers() {
            List<FavoriteCat> cats = createCats();
            List<User> users = new LinkedList<>();

            // User 1
            User user1 = new User();
            user1.name = "dominik";
            user1.email = "d.auinger@yelp.com";
            user1.bio = "i live in hell";
            user1.password = "12345";
            user1.favoriteCats = cats.subList(2, 3);
            users.add(user1);

            // User 2
            User user2 = new User();
            user2.name = "fabian";
            user2.email = "f.ettinger@yahoo.net";
            user2.bio = "me too";
            user2.password = "54321";
            user2.favoriteCats = cats.subList(0, 2);
            users.add(user2);

            // User 3
            User user3 = new User();
            user3.name = "lorenz";
            user3.email = "l.lanzinger@gmx.at";
            user3.bio = "i love lanzen";
            user3.password = "13249";
            user3.favoriteCats = cats.subList(1, 2);
            users.add(user3);

            // User 4
            User user4 = new User();
            user4.name = "marcel";
            user4.email = "m.pouget@gmail.de";
            user4.bio = "i too love lanzen";
            user4.password = "214365";
            user4.favoriteCats = cats.subList(3, 4);
            users.add(user4);

            return users;
        }

        public List<Post> createPosts(FindIterable<User> user) {
            List<Post> posts = new LinkedList<>();

            // Post 1
            Post post1 = new Post();
            post1.content = "Hello, this is my first post. I hope you like it.";
            post1.timestamp = LocalDateTime.now();
            post1.userId = Objects.requireNonNull(user.first()).id;
            posts.add(post1);

            // Post2
            Post post2 = new Post();
            post2.content = "Yo yo, waddup. Hope you enjoy your day!";
            post2.timestamp = LocalDateTime.now();
            post2.userId = Objects.requireNonNull(user.skip(1).first()).id;
            posts.add(post2);

            return posts;
        }

        public MongoDatabase getDatabase() {
            return mongoClient.getDatabase("asoziales-netzwerk");
        }
    }
}
