package com.interview.library_management.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LoginDto (
        @NotNull
        String username,
        @NotNull
        @Length(min = 8, max = 20)
        String password
) {
}
