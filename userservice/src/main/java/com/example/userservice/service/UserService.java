package com.example.userservice.service;

import com.example.userservice.dto.request.PatchUserRequest;
import com.example.userservice.dto.request.UpdateRequest;
import com.example.userservice.dto.response.UserResponse;

import java.util.Optional;

public interface UserService {

    Optional<UserResponse> findById(String id);

    UserResponse updateById(String id, UpdateRequest request);

    void deleteById(String id);

    void updatePassword(String id, String newPassword);
}
