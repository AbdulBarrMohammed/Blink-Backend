package com.blink.blink_backend.services;

import com.blink.blink_backend.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> listUser();
    User createUser(User user);
    Optional<User> getUser(UUID id);
    User updateUser(UUID userId, User user);
    void deleteUser(UUID userId);
}
