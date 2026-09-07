package com.interview.library_management.exceptions;

public class DifferentPasswordException extends RuntimeException {
    public DifferentPasswordException(String message) {
        super(message);
    }
}
