package com.example.userservice.controller;

import com.example.userservice.dto.request.PatchUserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/v1/users")
    public List<UserResponse> getUsers(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        return adminService.getAllUsers(size, page);
    }

    @GetMapping("/v1/users/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return adminService.findById(id);
    }

    @PatchMapping("/v1/users/{id}/role")
    public UserResponse patchUserRole(@PathVariable String id,
                                      @Validated({PatchUserRequest.PatchRoleValidation.class}) @RequestBody PatchUserRequest request) {
        return adminService.updateRole(id, request);
    }

    @DeleteMapping("/v1/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable String id) {
        adminService.deleteUser(id);
    }


}
