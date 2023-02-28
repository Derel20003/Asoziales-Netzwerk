package com.asozial.api;

import com.asozial.model.Interaction;
import com.asozial.model.Post;
import com.asozial.model.dto.InteractionDTO;
import com.asozial.model.dto.PostDTO;
import com.asozial.service.PostService;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/post")
@RolesAllowed("user")
public class PostResource {

    @Inject
    PostService postService;
    @Inject
    JsonWebToken jwt;

    @GET
    @Path("{id}")
    public Post get(@PathParam("id") String id) {
        return postService.findById(id);
    }

    @GET
    @Path("/all")
    public Response getAll() {
        return Response.ok(this.postService.getLatestPosts(0)).build();
    }

    @GET
    @Path("/user/{id}")
    public Response getPostsForUser(@PathParam("id") String id) {
        return Response.ok(this.postService.getPostsByUser(id)).build();
    }

    @GET
    @Path("/find")
    public List<Post> find(@QueryParam("content") String content) {
        return postService.search(content);
    }

    @POST
    public Response add(PostDTO p) {
        Post post = new Post();
        post.content = p.content;
        post.timestamp = LocalDateTime.now();
        post.userId = new ObjectId(jwt.getName());
        if (p.commentOn != null)
            post.commentOn = new ObjectId(p.commentOn);
        postService.persist(post);
        return Response.created(URI.create(post.id.toString())).build();
    }

    @POST
    @Path("/interact")
    public Response interact(InteractionDTO i) {
        postService.findByIdOptional(i.postId).ifPresent(p -> {
            Interaction interaction = new Interaction();
            interaction.interactorId = new ObjectId(jwt.getName());
            interaction.type = i.type;
            interaction.timestamp = LocalDateTime.now();
            p.interactions.add(interaction);
            postService.update(p);
        });
        return Response.status(201).build();
    }

    @PUT
    public void update(Post post) {
        postService.findByIdOptional(post.id.toHexString()).ifPresent(p -> {
            ObjectId id = new ObjectId(jwt.getName());
            if (p.userId.equals(id)) {
                post.userId = id;
                postService.update(post);
            }
        });
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        postService.findByIdOptional(id)
                .ifPresent(post -> {
                    if (post.userId.equals(new ObjectId(jwt.getName()))) {
                        postService.delete(post);
                    }
                });
    }
}
