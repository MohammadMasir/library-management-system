package com.interview.library_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterDto(
        @NotNull (message = "Username cannot be Empty!!")
        String username,
        @NotNull (message = "Email cannot be Empty!!")
        @Email
        String email,
        @NotNull (message = "Fullname cannot be Empty!1")
        String fullname,
        @NotNull (message = "Password cannot be Empty!!")
        @Length(max = 20, min = 8, message = "Password should be 8-20 characters long.")
        String password,
        @NotNull(message = "Confirm password cannot be Empty!!")
        @Length(max = 20, min = 8)
        String confirmPassword
) {
}
