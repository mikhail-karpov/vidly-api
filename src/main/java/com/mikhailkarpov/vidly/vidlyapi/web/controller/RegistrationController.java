package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
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

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String matchingPassword = request.getMatchingPassword();

        if (!password.equals(matchingPassword))
            return ResponseEntity.badRequest().body("Passwords don't match");

        UserDto user = userService.register(email, password);
        return ResponseEntity.ok().body(user);
    }
}
