package com.daniel.userAuthFlow.exception;

public class UserAlreadyInOrganisationException extends RuntimeException {
    public UserAlreadyInOrganisationException(String exp) {
        super(exp);
    }
}
