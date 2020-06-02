package com.mikhailkarpov.vidly.vidlyapi.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long id) {
        super("Movie not found by id = " + id);
    }
}
