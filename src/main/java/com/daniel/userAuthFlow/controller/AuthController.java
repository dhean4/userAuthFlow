package com.daniel.userAuthFlow.controller;

import com.daniel.userAuthFlow.dto.AppResponse;
import com.daniel.userAuthFlow.dto.LoginRequest;
import com.daniel.userAuthFlow.dto.RegisterRequest;
import com.daniel.userAuthFlow.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

//    @PostMapping("/register")
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<AppResponse> register(
            @RequestBody @Valid RegisterRequest registerRequest
    ) {
        try {
            var auth = authenticationService.register(registerRequest);
            return ResponseEntity.created(URI.create("/auth/register"))
                    .body(auth);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Registration unsuccessful");
        }
    }

//    @PostMapping("/login")
@PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<AppResponse> login(
            @RequestBody @Valid LoginRequest loginRequest
    ) {
        try {
            var auth = authenticationService.login(loginRequest);
            return ResponseEntity.ok().body(auth);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Authentication failed");
        }
    }
}
