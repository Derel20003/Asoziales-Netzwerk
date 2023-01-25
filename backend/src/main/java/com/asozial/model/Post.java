package com.asozial.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class Post extends PanacheMongoEntity {

    @Size(max = 500)
    String content;

    LocalDateTime timestamp;

}
