package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRole;
import com.mikhailkarpov.vidly.vidlyapi.exception.MyBadRequestException;
import com.mikhailkarpov.vidly.vidlyapi.security.JwtService;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("account")
@Slf4j
public class AccountController {

    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AccountController(UserService userService,
                             JwtService jwtService,
                             AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@Valid @RequestBody RegistrationRequest request) {
        log.debug("Request for registering new user: {}", request);

        String email = request.getEmail();
        String password = request.getPassword();

        UserDto created = userService.create(email, password, Collections.singleton(UserRole.USER));
        String jwt = authenticateInternal(email, password);

        return new AuthenticationResponse(jwt, created);
    }

    @PostMapping("/auth")
    public AuthenticationResponse authenticate(@Valid @RequestBody AuthenticationRequest request) {
        log.debug("Request for authentication: {}", request);

        String email = request.getEmail();
        String password = request.getPassword();

        String jwt = authenticateInternal(email, password);
        UserDto user = userService.findByEmail(email);

        return new AuthenticationResponse(jwt, user);
    }

    private String authenticateInternal(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtService.generateToken(username);

        } catch (AuthenticationException e) {
            throw new MyBadRequestException("Invalid email or password");
        }
    }
}
