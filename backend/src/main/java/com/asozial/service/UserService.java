package com.asozial.service;

import com.asozial.model.User;
import com.asozial.repository.UserRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;


    public Optional<User> findByEmail(String email) {
        return userRepository.find("email", email)
                .stream()
                .findAny();
    }

    public User findById(String id) {
        return userRepository.findById(new ObjectId(id));
    }

    public List<User> listAll() {
        return userRepository.listAll();
    }

    public List<User> listByName(String name) {
        return userRepository.list("name", name);
    }

    public void persist(User user) {
        userRepository.persist(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public Optional<User> findByIdOptional(String id) {
        return userRepository.findByIdOptional(new ObjectId(id));
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
