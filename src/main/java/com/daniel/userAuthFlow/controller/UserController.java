package com.daniel.userAuthFlow.controller;

import com.daniel.userAuthFlow.dto.AppResponse;
import com.daniel.userAuthFlow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getAllowedUser(
            @PathVariable(name = "id") String id,
            Authentication loggedInUser
    ) {
        return ResponseEntity.ok(userService.allowedUsers(id, loggedInUser));
    }
}
