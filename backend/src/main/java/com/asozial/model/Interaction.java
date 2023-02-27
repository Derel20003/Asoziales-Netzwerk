package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class Interaction extends PanacheMongoEntity {

    public InteractionType type;
    public LocalDateTime timestamp;
    public ObjectId interactorId;

}
