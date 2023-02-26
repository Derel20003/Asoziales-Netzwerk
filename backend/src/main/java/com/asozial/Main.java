package com.asozial;

import com.asozial.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

import javax.inject.Inject;
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

            // ADD DATA TO COLLECTIONS
            getDatabase().getCollection("user", User.class).insertMany(createUsers());

            Quarkus.waitForExit();
            return 0;
        }

        public List<User> createUsers() {
            List<User> users = new LinkedList<>();

            User user = new User();
            user.name = "dominik";
            user.email = "d.auinger@yelp.com";
            user.bio = "i live in hell";
            user.password = "12345";

            users.add(user);

            return users;
        }

        public MongoDatabase getDatabase() {
            return mongoClient.getDatabase("asoziales-netzwerk");
        }
    }
}
