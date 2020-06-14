package com.mikhailkarpov.vidly.vidlyapi.web.dto;

public class AuthenticationResponse {

    private String jwt;
    private UserDto user;

    public AuthenticationResponse(String jwt, UserDto user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public UserDto getUser() {
        return user;
    }
}
