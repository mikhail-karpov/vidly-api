package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private String jwt;
    private String email;

}
