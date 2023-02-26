package com.asozial;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

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
            Quarkus.waitForExit();
            return 0;
        }

        public MongoDatabase getDatabase() {
            return mongoClient.getDatabase("asoziales-netzwerk");
        }
    }
}
