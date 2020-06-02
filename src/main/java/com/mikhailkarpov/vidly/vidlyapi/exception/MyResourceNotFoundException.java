package com.mikhailkarpov.vidly.vidlyapi.exception;

public abstract class MyResourceNotFoundException extends RuntimeException {

    public MyResourceNotFoundException(String message) {
        super(message);
    }

    public MyResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
