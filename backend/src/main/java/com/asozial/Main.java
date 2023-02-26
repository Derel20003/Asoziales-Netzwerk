package com.asozial;

import com.asozial.model.Post;
import com.asozial.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
            getDatabase().getCollection("post", Post.class).insertMany(createPosts());

            Quarkus.waitForExit();
            return 0;
        }

        public List<User> createUsers() {
            List<User> users = new LinkedList<>();

            // User 1
            User user1 = new User();
            user1.name = "dominik";
            user1.email = "d.auinger@yelp.com";
            user1.bio = "i live in hell";
            user1.password = "12345";
            users.add(user1);

            // User 2
            User user2 = new User();
            user2.name = "fabian";
            user2.email = "f.ettinger@yahoo.net";
            user2.bio = "me too";
            user2.password = "54321";
            users.add(user2);

            // User 3
            User user3 = new User();
            user3.name = "lorenz";
            user3.email = "l.lanzinger@gmx.at";
            user3.bio = "i love lanzen";
            user3.password = "13249";
            users.add(user3);

            // User 4
            User user4 = new User();
            user4.name = "marcel";
            user4.email = "m.pouget@gmail.de";
            user4.bio = "i too love lanzen";
            user4.password = "214365";
            users.add(user4);

            return users;
        }

        public List<Post> createPosts() {
            List<Post> posts = new LinkedList<>();

            // Post 1
            Post post1 = new Post();
            post1.content = "Hello, this is my first post. I hope you like it.";
            post1.timestamp = LocalDateTime.now();
            posts.add(post1);

            // Post2
            Post post2 = new Post();
            post2.content = "Yo yo, waddup. Hope you enjoy your day!";
            post2.timestamp = LocalDateTime.now();
            posts.add(post2);

            return posts;
        }

        public MongoDatabase getDatabase() {
            return mongoClient.getDatabase("asoziales-netzwerk");
        }
    }
}
