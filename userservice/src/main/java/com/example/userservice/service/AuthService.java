package com.example.userservice.service;

import com.example.userservice.dto.request.LoginRequest;
import com.example.userservice.dto.request.UserRegistrationRequest;
import com.example.userservice.dto.response.UserResponse;

public interface AuthService {
    String login(LoginRequest request);

    UserResponse register(UserRegistrationRequest request);

    void logout(String userId);
}
