package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.User;
import com.mikhailkarpov.vidly.vidlyapi.security.JwtService;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AuthenticationResponse;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registrations")
public class RegistrationController {

    private UserService userService;
    private JwtService jwtService;

    public RegistrationController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String matchingPassword = request.getMatchingPassword();

        if (!password.equals(matchingPassword))
            return ResponseEntity.badRequest().body("Passwords don't match");

        userService.register(email, password);

        String jwt = jwtService.generateToken(email);
        AuthenticationResponse response = new AuthenticationResponse(jwt, email);

        return ResponseEntity.ok().body(response);
    }
}
