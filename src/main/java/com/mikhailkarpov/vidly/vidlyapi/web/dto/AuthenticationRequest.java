package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // for unmarshalling
@Data
public class AuthenticationRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "email='" + email + '\'' +
                ", password='" + "[SECURED]" + '\'' +
                '}';
    }
}
