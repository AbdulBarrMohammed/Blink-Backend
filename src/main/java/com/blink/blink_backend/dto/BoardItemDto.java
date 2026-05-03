package com.blink.blink_backend.dto;

import java.util.UUID;

public record BoardItemDto(
        UUID id,
        double x,
        double y
) {
}
