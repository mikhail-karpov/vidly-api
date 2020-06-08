package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.security.JwtService;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AuthenticationRequest;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AuthenticationResponse;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtService jwtService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            String email = request.getEmail();
            String password = request.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDto user = userService
                    .findByEmail(email)
                    .orElseThrow(() -> {
                        String errMsg = String.format("User with email %s not found", email);
                        return new UsernameNotFoundException(errMsg);
            });

            String jwt = jwtService.generateToken(user.getEmail());
            AuthenticationResponse response = new AuthenticationResponse(jwt, user);

            return ResponseEntity.ok().body(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }
}
