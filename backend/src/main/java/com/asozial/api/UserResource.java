package com.asozial.api;

import com.asozial.model.User;
import com.asozial.service.UserService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
public class UserResource {

    @Inject
    UserService userService;
    @Inject
    JsonWebToken jwt;

    @GET
    @Path("{id}")
    public User get(@PathParam("id") String id) {
        return userService.findById(id);
    }

    @GET
    @Path("/profile/{id}")
    public Document getProfile(@PathParam("id") String id) {
        return userService.getUserProfile(id);
    }


    @GET
    @Path("/all")
    public List<User> getAll() {
        return userService.listAll();
    }

    @GET
    @Path("/find")
    public List<User> find(@QueryParam("name") String name) {
        return userService.listByName(name);
    }

    @POST
    public Response add(User user) {
        userService.persist(user);
        return Response.created(URI.create("/user/" + user.id.toString())).build();
    }

    @PUT
    @RolesAllowed("user")
    public void update(User user) {
        user.id = new ObjectId(jwt.getName());
        userService.update(user);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("user")
    public void delete(@PathParam("id") String id) {
        if (id.equals(jwt.getName())) {
            userService.findByIdOptional(id)
                    .ifPresent(user -> userService.delete(user));
        }
    }
}
