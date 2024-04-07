package com.carjunior.manageparking.domain.dto.user;

import com.carjunior.manageparking.domain.entity.enums.Permission;
import com.carjunior.manageparking.domain.entity.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserCreateUpdateDto(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String surname,
        @NotNull @NotEmpty @Email String email,
        @NotNull @Size(min = 1) Set<RoleType> roles,
        @NotNull @Size(min = 1) Set<Permission> permissions,
        @NotNull @NotEmpty @Size(min = 8, max = 50) String password
) {
    @Override
    public String name() {
        return name.trim();
    }

    @Override
    public String surname() {
        return surname.trim();
    }

    @Override
    public String email() {
        return email.trim();
    }

    @Override
    public String password() {
        return password.trim();
    }
}

