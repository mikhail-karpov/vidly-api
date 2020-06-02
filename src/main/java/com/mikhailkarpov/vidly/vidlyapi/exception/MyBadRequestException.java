package com.mikhailkarpov.vidly.vidlyapi.exception;

public class MyBadRequestException extends RuntimeException {

    public MyBadRequestException(String message) {
        super(message);
    }

    public MyBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBadRequestException(Throwable cause) {
        super(cause);
    }
}
