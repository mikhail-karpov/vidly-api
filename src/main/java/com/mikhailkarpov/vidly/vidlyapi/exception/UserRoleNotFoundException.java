package com.mikhailkarpov.vidly.vidlyapi.exception;

public class UserRoleNotFoundException extends MyResourceNotFoundException {

    public UserRoleNotFoundException(Long id) {
        super("Role not found by id " + id);
    }
}
