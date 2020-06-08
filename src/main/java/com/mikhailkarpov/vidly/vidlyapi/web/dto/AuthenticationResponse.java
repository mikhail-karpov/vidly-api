package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private UserDto user;
}
