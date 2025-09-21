package com.example.userservice.dto.response;

import com.example.userservice.enums.Role;
import com.example.userservice.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    public String id;
    public String username;
    public String email;
    public Set<Role> roles;
    public UserStatus status;
    public String phone;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
