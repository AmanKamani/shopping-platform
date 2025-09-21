package com.example.userservice.service;

import com.example.userservice.dto.request.PatchUserRequest;
import com.example.userservice.dto.response.UserResponse;

import java.util.List;

public interface AdminService {

    List<UserResponse> getAllUsers(int size, int page);

    UserResponse findById(String id);

    UserResponse updateRole(String id, PatchUserRequest request);

    void deleteUser(String id);
}
