package com.daniel.userAuthFlow.exception;

import java.util.List;

public record ExceptionResponse(
        List<ValidationError> errors
) {
}
