package com.asozial.model.dto;

import javax.validation.constraints.Size;

public class PostDTO {

    @Size(max = 500)
    public String content;
    public String userId;
}
