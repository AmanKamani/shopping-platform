package com.example.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
        @NotBlank
        @Size(min = 4, max = 50)
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, max = 20)
        String password,

        @Pattern(regexp = "[0-9]{10}", message = "Invalid format of mobile number")
        String phone
) {

}
