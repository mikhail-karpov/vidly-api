package com.mikhailkarpov.vidly.vidlyapi.domain.entity;

public enum UserRole {

    USER("USER"), ADMIN("ADMIN");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
