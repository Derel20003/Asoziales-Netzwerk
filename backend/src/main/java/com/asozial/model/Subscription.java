package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class Subscription extends PanacheMongoEntity {
    public ObjectId subscribedToId;
    public LocalDateTime timestamp;
}
