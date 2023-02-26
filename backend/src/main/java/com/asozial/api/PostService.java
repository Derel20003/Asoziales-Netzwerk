package com.asozial.api;

import com.asozial.model.Post;
import com.asozial.model.dto.PostDTO;
import com.asozial.repository.PostRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/post")
public class PostService {

    @Inject
    PostRepository postRepository;

    @GET
    @Path("/all")
    public Response getAll() {
        return Response.ok(this.postRepository.listAll()).build();
    }

    @POST
    public Response add(PostDTO post) {
        Post newPost = new Post();
        newPost.content = post.content;
        newPost.timestamp = LocalDateTime.now();
        this.postRepository.persist(newPost);
        return Response.ok().build();
    }
}
