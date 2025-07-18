package com.daniel.userAuthFlow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
public record UserResponse (
        String userId,
        String firstName,
        String lastName,
        String email,
        String phone
){
}
