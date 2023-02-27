package com.asozial.repository;

import com.asozial.model.Post;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PostRepository implements PanacheMongoRepository<Post> {
    public List<Post> getLatestPosts(int page) {
        return findAll(Sort.by("timestamp", Sort.Direction.Descending))
                .page(Page.of(page, 50))
                .list();
    }

    public List<Post> getPostsByUser(ObjectId userId) {
        return find("userId",
                Sort.by("timestamp", Sort.Direction.Descending), userId).list();
    }
}
