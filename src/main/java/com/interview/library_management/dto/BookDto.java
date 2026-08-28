package com.interview.library_management.dto;

import jakarta.validation.constraints.NotNull;

public record BookDto(
        @NotNull String title,
        @NotNull String authorName,
        @NotNull String description
) {
}
