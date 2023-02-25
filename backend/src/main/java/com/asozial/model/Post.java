package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@MongoEntity(collection = "post")
public class Post extends PanacheMongoEntity {

    @Size(max = 500)
    String content;

    LocalDateTime timestamp;

}
