package com.asozial.repository;

import com.asozial.model.FavoriteCat;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FavoriteCatRepository implements PanacheMongoRepository<FavoriteCat> {
}
