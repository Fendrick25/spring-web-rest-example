package com.example.demoservice.service;

import com.example.demoservice.dto.request.RegisterRequest;
import com.example.demoservice.dto.response.RegisterResponse;
import com.example.demoservice.entity.UserEntity;
import com.example.demoservice.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserJpaRepository userJpaRepository;

    public RegisterResponse register(RegisterRequest registerRequest){
        UserEntity savedUser = userJpaRepository.save(UserEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .username(registerRequest.getUsername())
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .token(UUID.randomUUID().toString())
                .build());

        return RegisterResponse.builder()
                .username(savedUser.getUsername())
                .token(savedUser.getToken())
                .build();
    }
}
