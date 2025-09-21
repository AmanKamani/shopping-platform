package com.example.userservice.dto.request;

import jakarta.validation.constraints.Pattern;

public record UpdateRequest(
        @Pattern(regexp = "[0-9]{10}", message = "Invalid format of mobile number")
        String phone
) {}
