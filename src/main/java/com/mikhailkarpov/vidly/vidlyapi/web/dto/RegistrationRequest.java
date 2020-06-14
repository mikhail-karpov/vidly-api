package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.validation.EqualPassword;
import com.mikhailkarpov.vidly.vidlyapi.validation.Password;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@EqualPassword
public class RegistrationRequest {

    @Email(message = "Invalid email")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Password(message = "Password must contain at least one upper-case, one lower-case and one special symbol characters")
    private String password;

    @NotEmpty(message = "Matching password is required")
    private String matchingPassword;

}
