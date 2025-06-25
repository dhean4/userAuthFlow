package com.daniel.userAuthFlow.exception;

public record ValidationError(
        String field,
        String message
) {
}
