package com.asozial.model;


import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "cats")
public class FavoriteCat extends PanacheMongoEntity {

    public String name;

    public String imgURL;
}
