package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.Data;

@Data
public class AccountRegistrationRequest {

    private String email;
    private String password;
    private String name;
}
