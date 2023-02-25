package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "user")
public class User extends PanacheMongoEntity {

    String name;
    String email;
    String password;
    String bio;
}
