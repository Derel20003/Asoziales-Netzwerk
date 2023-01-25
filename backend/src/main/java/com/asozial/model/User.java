package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class User extends PanacheMongoEntity {

    String name;
    String email;
    String password;
    String bio;
}
