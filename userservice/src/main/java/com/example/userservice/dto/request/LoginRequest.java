package com.example.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        String identity, // can be email / username
        @NotBlank
        String password
) {
}
