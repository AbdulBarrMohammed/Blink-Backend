package com.blink.blink_backend.mappers.impl;

import com.blink.blink_backend.dto.UserDto;
import com.blink.blink_backend.entities.User;
import com.blink.blink_backend.mappers.BoardMapper;
import com.blink.blink_backend.mappers.PictureMapper;
import com.blink.blink_backend.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapperImpl implements UserMapper {
    private final PictureMapper pictureMapper;
    private final BoardMapper boardMapper;

    public UserMapperImpl(PictureMapper pictureMapper, BoardMapper boardMapper) {
        this.pictureMapper = pictureMapper;
        this.boardMapper = boardMapper;
    }

    @Override
    public User fromDto(UserDto userDto) {
        return new User(
                Optional.ofNullable(userDto.pictures())
                        .map(pictures -> pictures.stream()
                                .map(pictureMapper::fromDto)
                                .toList()
                        ).orElse(null),
                Optional.ofNullable(userDto.boards())
                        .map(boards -> boards.stream()
                                .map(boardMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                userDto.email(),
                userDto.id()

        );
    }

    @Override
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                Optional.ofNullable(user.getBoards())
                        .map(boards ->
                                boards.stream().map(boardMapper::toDto).toList()).orElse(null),
                Optional.ofNullable(user.getPictures())
                        .map(pictures ->
                                pictures.stream().map(pictureMapper::toDto).toList()).orElse(null)

        );
    }
}
