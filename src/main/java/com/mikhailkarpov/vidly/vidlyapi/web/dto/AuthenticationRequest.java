package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
