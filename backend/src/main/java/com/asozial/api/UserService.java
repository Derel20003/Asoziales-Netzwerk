package com.asozial.api;

import com.asozial.model.User;
import com.asozial.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
public class UserService {

    @Inject
    UserRepository userRepository;

    @GET
    @Path("/all")
    public Response getAll() {
        return Response.ok(userRepository.listAll()).build();
    }

    @POST
    public Response add(User user) {
        this.userRepository.persist(user);

        return Response.ok().build();
    }
}
