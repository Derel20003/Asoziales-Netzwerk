package com.asozial.service;

import com.asozial.model.Post;
import com.asozial.repository.PostRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Sorts.descending;

@ApplicationScoped
public class PostService {

    @Inject
    PostRepository postRepository;

    public List<Post> getLatestPosts(int page) {
        return postRepository.getLatestPosts(page);
    }

    public List<Post> getPostsByUser(String userId) {
        return postRepository.getPostsByUser(new ObjectId(userId));
    }

    public Post findById(String id) {
        return postRepository.findById(new ObjectId(id));
    }

    public List<Post> search(String content) {
        return postRepository.search(content);
    }

    public void persist(Post post) {
        postRepository.persist(post);
    }

    public Optional<Post> findByIdOptional(String id) {
        return postRepository.findByIdOptional(new ObjectId(id));
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}
