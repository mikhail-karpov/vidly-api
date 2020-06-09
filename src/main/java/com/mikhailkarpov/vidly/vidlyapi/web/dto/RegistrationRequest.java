package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @Email(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;

    @Size(min = 8, message = "Password must be alt least 8 characters long")
    @ValidPassword(message = "Password must contain at least one character, digit and special symbol")
    private String password;

    private String matchingPassword;
}
