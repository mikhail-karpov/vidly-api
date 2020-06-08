package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.security.JwtService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.ApiErrorDto;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AuthenticationRequest;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtService jwtService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserDetailsService userDetailsService,
                                    JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            String email = request.getEmail();
            String password = request.getPassword();

            UserDetails user = userDetailsService.loadUserByUsername(email);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities()));

            String jwt = jwtService.generateToken(email);
            AuthenticationResponse response = new AuthenticationResponse(jwt, email);

            return ResponseEntity.ok().body(response);

        } catch (AuthenticationException e) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ApiErrorDto errorDto = new ApiErrorDto(status, "Invalid email or password");

            return ResponseEntity.status(status).body(errorDto);
        }
    }
}
