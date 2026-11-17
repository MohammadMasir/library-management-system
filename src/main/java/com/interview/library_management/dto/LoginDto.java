package com.interview.library_management.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LoginDto (
        @NotNull (message = "Username cannot be Empty!")
        String username,
        @NotNull (message = "Password cannot be Empty!!")
        @Length(min = 8, max = 20, message = "Password should be 8-20 characters long.")
        String password
) {
}
