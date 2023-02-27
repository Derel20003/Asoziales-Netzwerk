package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.List;

@MongoEntity(collection = "user")
public class User extends PanacheMongoEntity {
    public String name;
    public String email;
    public String password;
    public String bio;
    public List<FavoriteCat> favoriteCats = new ArrayList<>();
    public List<Subscription> subscriptions = new ArrayList<>();
}
