package com.asozial.api;

import com.asozial.repository.PostRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
