package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.security.JwtService;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.ApiErrorDto;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AuthenticationRequest;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AuthenticationResponse;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.RegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequest request) {
        log.debug("Request for registering new user: {}", request);

        String email = request.getEmail();
        String password = request.getPassword();
        String matchingPassword = request.getMatchingPassword();

        if (!password.equals(matchingPassword))
            return ResponseEntity.badRequest().body("Passwords don't match");

        userService.register(email, password);

        return authenticateInternal(email, password);
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        log.debug("Request for authentication: {}", request);

        String email = request.getEmail();
        String password = request.getPassword();

        return authenticateInternal(email, password);
    }

    private ResponseEntity<Object> authenticateInternal(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            AuthenticationResponse response = new AuthenticationResponse(jwtService.generateToken(username), username);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            log.warn("Authentication exception: " + e.getMessage());

            HttpStatus status = HttpStatus.BAD_REQUEST;
            ApiErrorDto errorDto = new ApiErrorDto(status, "Invalid email or password");
            return ResponseEntity.status(status).body(errorDto);
        }
    }
}
