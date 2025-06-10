package com.daniel.userAuthFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank String firstName;
    @NotBlank  String lastName;
    @Email @NotBlank String email;
    @NotBlank  String password;
    private String phone;
}
