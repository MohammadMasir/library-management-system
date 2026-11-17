package com.interview.library_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDto(
        @NotNull (message = "Book id cannot be Empty!!") Long id,
        @NotBlank(message = "Book title cannot be Empty!!") String title,
        @NotBlank (message = "Author name cannot be Empty!!") String authorName,
        @NotBlank (message = "Description cannot be Empty!!") String description
) {
    public BookDto(
            @NotBlank (message = "Book title cannot be Empty!!") String title,
            @NotBlank (message = "Author name cannot be Empty!!") String authorName,
            @NotBlank (message = "Description cannot be Empty!!") String description
    ){
        this(0L,title,authorName,description);
    }
}
