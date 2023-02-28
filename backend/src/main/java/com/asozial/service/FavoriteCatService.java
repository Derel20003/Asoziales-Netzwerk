package com.asozial.service;

import com.asozial.model.FavoriteCat;
import com.asozial.repository.FavoriteCatRepository;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FavoriteCatService {
    @Inject
    FavoriteCatRepository favoriteCatRepository;

}
