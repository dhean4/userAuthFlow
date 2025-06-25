package com.daniel.userAuthFlow.service;

import com.daniel.userAuthFlow.dto.AppResponse;
import com.daniel.userAuthFlow.dto.LoginRequest;
import com.daniel.userAuthFlow.dto.RegisterRequest;
import com.daniel.userAuthFlow.entity.User;
import com.daniel.userAuthFlow.exception.EmailAlreadyExistsException;
import com.daniel.userAuthFlow.jwt.JwtService;
import com.daniel.userAuthFlow.mapper.UserMapper;
import com.daniel.userAuthFlow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Transactional
    public AppResponse register(RegisterRequest registerRequest) {
        validateEmailNotExist(registerRequest.email());

        User user = userMapper.createUserFromRegisterRequest(registerRequest);
//        userRepository.save(user);
        String result = authenticate(registerRequest.email(), registerRequest.password(), user);
        return buildAuthResponse(result, user, "register");
    }

    @Transactional
    public AppResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = authenticate(loginRequest.email(), loginRequest.password(), user);


        return buildAuthResponse(token, user, "login");
    }

    private void validateEmailNotExist(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    private String authenticate(String email, String password, User user) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(email, password);

        try {
            authenticationManager.authenticate(authenticationRequest);

            return jwtService.generateToken(user);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("");
        }
    }

    private AppResponse buildAuthResponse(String result, User user, String path) {
        return AppResponse.builder()
                .status("success")
                .message(path.equals("login") ? "Login successful"
                        : "Registration successful")
                .data(userMapper.toUserData(result, user))
                .build();
    }
}
