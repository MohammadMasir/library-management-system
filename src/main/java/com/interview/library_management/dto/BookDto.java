package com.interview.library_management.dto;

import jakarta.validation.constraints.NotNull;

public record BookDto(
        @NotNull (message = "Book id cannot be Empty!!") Long id,
        @NotNull (message = "Book title cannot be Empty!!") String title,
        @NotNull (message = "Author name cannot be Empty!!") String authorName,
        @NotNull (message = "Description cannot be Empty!!") String description
) {
    public BookDto(
            @NotNull (message = "Book title cannot be Empty!!") String title,
            @NotNull (message = "Author name cannot be Empty!!") String authorName,
            @NotNull (message = "Description cannot be Empty!!") String description
    ){
        this(0L,title,authorName,description);
    }
}
