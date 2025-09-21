package com.example.userservice.dto.request;

import com.example.userservice.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PatchUserRequest {

    @NotNull(groups = PatchRoleValidation.class)
    private PatchType type;
    @NotEmpty(groups = PatchRoleValidation.class)
    private Set<Role> roles;

    @NotBlank(groups = PatchPasswordValidation.class)
    @Size(min = 8, max = 20, groups = PatchPasswordValidation.class)
    private String password;

    @NotBlank(groups = PatchPasswordValidation.class)
    @Size(min = 8, max = 20, groups = PatchPasswordValidation.class)
    private String oldPassword;


    public enum PatchType {
        ADD,
        REMOVE
    }


    public interface PatchRoleValidation {}

    public interface PatchPasswordValidation {}
}
