package com.asozial.repository;

import com.asozial.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

    @Inject
    @ConfigProperty(name = "quarkus.mongodb.database")
    String database;
    @Inject
    MongoClient mongoClient;
    public Document getUserProfile(ObjectId id) {
        var pipeline = List.of(
                Aggregates.match(new Document("_id", id)),
                Aggregates.lookup("post", "_id", "userId", "posts"),
                Aggregates.project(Document.parse("{bio: 1,favoriteCats: 1,name: 1,posts: 1,subscriptions: 1}"))
        );
        var doc = getDatabase().getCollection("user")
                .aggregate(pipeline)
                .first();
        return doc;
    }
    public MongoDatabase getDatabase() {
        return mongoClient.getDatabase(database);
    }

    public long getSubscriberCount(ObjectId oid) {
        return find(
                    new Document("subscriptions",
                        new Document("$elemMatch",
                            new Document("subscribedToId", oid))))
                .count();
    }
}
