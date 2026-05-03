package com.blink.blink_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PictureDto(
        UUID id,
        String imageUrl,
        String sourceLink,
        LocalDateTime capturedAt
) {
}
