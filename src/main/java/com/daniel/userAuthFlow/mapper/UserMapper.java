package com.daniel.userAuthFlow.mapper;

import com.daniel.userAuthFlow.dto.DataResponse;
import com.daniel.userAuthFlow.dto.RegisterRequest;
import com.daniel.userAuthFlow.dto.UserResponse;
import com.daniel.userAuthFlow.entity.Organisation;
import com.daniel.userAuthFlow.entity.User;
import com.daniel.userAuthFlow.repository.OrganisationRepository;
import com.daniel.userAuthFlow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class  UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OrganisationRepository organisationRepository;

    public User createUserFromRegisterRequest(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .phone(registerRequest.phone())
                .organisations(new ArrayList<>())
                .build();

        user = userRepository.save(user);

        Organisation organisation = new Organisation();
        organisation.setName(registerRequest.firstName() + "'s Organisation");
        organisation.setCreator(user);
        // use a mutable list...
        organisation.setUsers(new ArrayList<>(List.of(user)));
        organisation = organisationRepository.save(organisation);

        // using a mutable list again
        user.setOrganisations(new ArrayList<>(List.of(organisation)));
        user = userRepository.save(user);
        return user;
    }

    public DataResponse toUserData(String result, User user) {
        return DataResponse.builder()
                .accessToken(result)
                .user(
                        UserResponse.builder()
                                .userId((user.getUserId()))
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .email(user.getEmail())
                                .phone(user.getPhone())
                                .build()
                )
                .build();
    }
}
