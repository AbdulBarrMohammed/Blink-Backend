package com.blink.blink_backend.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
