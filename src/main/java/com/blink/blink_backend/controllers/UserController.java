package com.blink.blink_backend.controllers;


import com.blink.blink_backend.dto.BoardDto;
import com.blink.blink_backend.dto.UserDto;
import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.entities.User;
import com.blink.blink_backend.mappers.UserMapper;
import com.blink.blink_backend.services.UserService;
import com.blink.blink_backend.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping
    public List<UserDto> userLists() {
        return userService.listUser()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {

        User createdUser = userService.createUser(
                userMapper.fromDto(userDto)
        );

        return userMapper.toDto(createdUser);

    }

    @GetMapping(path = "/{user_id}")
    public Optional<UserDto> getUser(@PathVariable("user_id") UUID userId) {
        return userService.getUser(userId).map(userMapper::toDto);
    }

    @PutMapping(path = "/{user_id}")
    public UserDto updatedUser(
            @PathVariable("user_id") UUID userId,
            @RequestBody UserDto userDto
    )
    {
        User updatedUser = userService.updateUser(
                userId,
                userMapper.fromDto(userDto)

        );

        return userMapper.toDto(updatedUser);
    }

    @DeleteMapping(path = "/{user_id}")
    public void deleteUser(@PathVariable("user_id") UUID userId) {
        userService.deleteUser(userId);
    }
}
