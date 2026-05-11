package com.blink.blink_backend.services.impl;

import com.blink.blink_backend.entities.User;
import com.blink.blink_backend.repositories.UserRepository;
import com.blink.blink_backend.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User already has an ID");
        }

        if (user.getEmail() == null && user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must not be null");
        }

        //add password validation

        return userRepository.save(
                new User(
                        null,
                        null,
                        user.getPassword(),
                        user.getEmail(),
                        user.getId()
                )
        );
    }

    @Override
    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(UUID userId, User user) {
        return null;
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);

    }
}
