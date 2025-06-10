package com.daniel.userAuthFlow.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class AuthResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private String accessToken;
        private UserDto user;
    }
}
