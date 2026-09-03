package com.interview.library_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterDto(
        @NotNull String username,
        @NotNull @Email String email,
        @NotNull String fullname,
        @NotNull @Length(max = 20, min = 8) String password,
        @NotNull @Length(max = 20, min = 8) String confirmPassword
) {
}
