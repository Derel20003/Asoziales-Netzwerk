package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@MongoEntity(collection = "post")
public class Post extends PanacheMongoEntity {

    @Size(max = 500)
    public String content;

    public LocalDateTime timestamp;

    public List<Interaction> interactions;

}
