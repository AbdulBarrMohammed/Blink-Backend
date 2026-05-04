package com.blink.blink_backend.mappers;

import com.blink.blink_backend.dto.UserDto;
import com.blink.blink_backend.entities.User;

public interface UserMapper {

    User fromDto(UserDto userDto);
    UserDto toDto(User user);
}
