package com.blink.blink_backend.controllers;

import com.blink.blink_backend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleExceptions(
            RuntimeException ex, WebRequest request
    ) {
        ErrorResponse errorRespone = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)

        );
        return new ResponseEntity<>(errorRespone, HttpStatus.BAD_REQUEST);
    }
}
