package com.blink.blink_backend.controllers;


import com.blink.blink_backend.dto.UserDto;
import com.blink.blink_backend.mappers.UserMapper;
import com.blink.blink_backend.services.UserService;
import com.blink.blink_backend.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
