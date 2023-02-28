package com.asozial.repository;

import com.asozial.model.Post;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.bson.BsonType;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.annotations.Pos;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

@ApplicationScoped
public class PostRepository implements PanacheMongoRepository<Post> {

    @Inject
    @ConfigProperty(name = "quarkus.mongodb.database")
    String database;
    @Inject
    MongoClient mongoClient;
    public List<Post> getLatestPosts(int page) {
        var pipeline = List.of(
                Document.parse("{$match: {commentOn: null}}"),
                Aggregates.sort(descending("timestamp"))
        );
        List<Post> posts = new ArrayList<>();
        var iterator = getDatabase()
                .getCollection("post")
                .aggregate(pipeline, Post.class)
                .iterator();
        while (iterator.hasNext()) {
            Post next = iterator.next();
            posts.add(next);
        }
        return posts;
        /*return find("commentOn",
                Sort.by("timestamp", Sort.Direction.Descending), BsonType.NULL)
                .page(Page.of(page, 50))
                .list();*/
    }

    public List<Post> getPostsByUser(ObjectId userId) {
        return find("userId",
                Sort.by("timestamp", Sort.Direction.Descending), userId).list();
    }

    public List<Post> search(String query) {
        return find(new Document("$text", new Document("$search", query))).list();
    }

    public MongoDatabase getDatabase() {
        return mongoClient.getDatabase(database);
    }
}
