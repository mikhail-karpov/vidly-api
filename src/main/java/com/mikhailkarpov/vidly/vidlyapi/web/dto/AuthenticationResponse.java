package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthenticationResponse {

    private String jwt;
    private UserDto user;

    public AuthenticationResponse(String jwt, UserDto user) {
        this.jwt = jwt;
        this.user = user;
    }
}
