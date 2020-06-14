package com.mikhailkarpov.vidly.vidlyapi.exception;

public class UserNotFoundException extends MyResourceNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(Long userId) {
        super("User not found with id " + userId);
    }
}
