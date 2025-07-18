package com.daniel.userAuthFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank(message = "Firstname is needed for registration")
        String firstName,

        @NotBlank(message = "Lastname is needed for registration")
        String lastName,

        @Email(message = "Email is not properly formatted")
        @NotBlank(message = "Email is needed for registration")
        String email,

        @NotBlank(message = "Password is needed for registration")
        String password,

        String phone
) {
}
