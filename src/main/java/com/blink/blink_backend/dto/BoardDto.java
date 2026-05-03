package com.blink.blink_backend.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// Created Board Dto records to represent boards in our rest api
// Simple version of board data to send to front end
// Control what gets exposed to the frontend
public record BoardDto(
        UUID id,
        String title,
        LocalDateTime createdAt,
        List<BoardItemDto> boardItems
)
{


}
