package com.example.userservice.controller;

import com.example.userservice.dto.request.PatchUserRequest;
import com.example.userservice.dto.request.UpdateRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;


    @GetMapping("/v1/me")
    public UserResponse getProfile() {
        return userService.findById(extractUserId());
    }

    @PutMapping("/v1/me")
    public UserResponse updateUser(@Valid @RequestBody UpdateRequest request) {
        return userService.updateById(extractUserId(), request);
    }

    @PatchMapping("/v1/password")
    public void updatePassword(@Validated(PatchUserRequest.PatchPasswordValidation.class) @RequestBody PatchUserRequest request) {
        userService.updatePassword(extractUserId(), request.getOldPassword(), request.getPassword());
    }

    private String extractUserId() {
        // TODO: Extract user ID from token
        return "";
    }
}
