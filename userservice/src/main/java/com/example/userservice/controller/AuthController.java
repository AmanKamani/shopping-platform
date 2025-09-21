package com.example.userservice.controller;

import com.example.userservice.dto.request.LoginRequest;
import com.example.userservice.dto.request.UserRegistrationRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        return authService.register(request);
    }

    @PostMapping("/v1/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/v1/logout")
    public void logout() {
        // TODO: extract user id from token
        authService.logout("");
    }
}
