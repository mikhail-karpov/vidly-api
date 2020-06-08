package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationRequest {

    private String email;
    private String password;
}
