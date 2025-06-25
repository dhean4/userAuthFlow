package com.daniel.userAuthFlow.exception;

public record AuthErrorResponse (
        String status,
        String message,
        Integer statusCode
) {
}
