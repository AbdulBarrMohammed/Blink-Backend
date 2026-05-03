package com.blink.blink_backend.dto;

import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        List<BoardDto> boards,
        List<PictureDto> pictures
) {
}
