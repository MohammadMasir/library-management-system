package com.interview.library_management.exceptions;

public class BookExistsException extends RuntimeException {
    public BookExistsException(String message) {
        super(message);
    }
}
