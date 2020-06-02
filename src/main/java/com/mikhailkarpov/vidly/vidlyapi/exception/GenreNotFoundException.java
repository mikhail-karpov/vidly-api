package com.mikhailkarpov.vidly.vidlyapi.exception;

public class GenreNotFoundException extends MyResourceNotFoundException {

    public GenreNotFoundException(Long id) {
        super("Genre not found by id = " + id);
    }
}
