package com.mikhailkarpov.vidly.vidlyapi.exception;

import io.jsonwebtoken.JwtException;

public class JwtCannotBeTrustedException extends Exception {

    public JwtCannotBeTrustedException(String message) {
        super(message);
    }

    public JwtCannotBeTrustedException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtCannotBeTrustedException(Throwable cause) {
        super(cause);
    }
}
