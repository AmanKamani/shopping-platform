package com.example.userservice.mapper;

import com.example.userservice.dto.request.UserRegistrationRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.AppUser;
import com.example.userservice.enums.Role;
import com.example.userservice.enums.UserStatus;

import java.util.HashSet;

public final class UserMapper {

    private UserMapper() {
        // do nothin
    }

    public static AppUser fromDto(UserRegistrationRequest registrationRequest, String encodedPassword) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        AppUser appUser = new AppUser();
        appUser.setEmail(registrationRequest.email());
        appUser.setPassword(encodedPassword);
        appUser.setUsername(registrationRequest.username());
        appUser.setRoles(roles);
        appUser.setPhoneNumber(registrationRequest.phone() != null
                ? registrationRequest.phone()
                : null);
        appUser.setStatus(UserStatus.INACTIVE);
        return appUser;
    }

    public static UserResponse fromEntity(AppUser appUser) {
        return UserResponse.builder()
                .id(appUser.getId().toString())
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .roles(appUser.getRoles())
                .status(appUser.getStatus())
                .createdAt(appUser.getCreatedAt())
                .updatedAt(appUser.getUpdatedAt())
                .phone(String.valueOf(appUser.getPhoneNumber()))
                .build();
    }
}
