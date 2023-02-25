package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "user")
public class User extends PanacheMongoEntity {

    public String name;
    public String email;
    public String password;
    public String bio;
}
