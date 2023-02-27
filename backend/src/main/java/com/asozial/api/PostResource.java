package com.asozial.api;

import com.asozial.model.Post;
import com.asozial.model.dto.PostDTO;
import com.asozial.repository.PostRepository;
import org.bson.types.ObjectId;

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
public class PostResource {

    @Inject
    PostRepository postRepository;

    @GET
    @Path("{id}")
    public Post get(@PathParam("id") String id) {
        return postRepository.findById(new ObjectId(id));
    }

    @GET
    @Path("/all")
    public Response getAll() {
        return Response.ok(this.postRepository.listAll()).build();
    }

    @GET
    @Path("/find")
    public List<Post> find(@QueryParam("content") String content) {
        return postRepository.list("content", content);
    }

    @POST
    public Response add(PostDTO p) {
        Post post = new Post();
        post.content = p.content;
        post.timestamp = LocalDateTime.now();
        postRepository.persist(post);
        return Response.created(URI.create(post.id.toString())).build();
    }

    @PUT
    public void update(Post post) {
        postRepository.update(post);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        postRepository.findByIdOptional(new ObjectId(id))
                .ifPresent(post -> postRepository.delete(post));
    }
}