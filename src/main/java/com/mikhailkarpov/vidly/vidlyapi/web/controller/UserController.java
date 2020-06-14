package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.ApiError;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAll() {
        log.debug("Request for all users");
        return userService.findAll();
    }

    @GetMapping("{userId}")
    public UserDto findById(@PathVariable Long userId) {
        log.debug("Request for user with id {}", userId);
        return userService.findById(userId);
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.debug("Request for creating user: {}", userDto);
        return userService.create(userDto);
    }

    @PutMapping("{userId}")
    public ResponseEntity<Object> update(@PathVariable Long userId, @Valid @RequestBody UserDto userDto) {
        log.debug("Request for updating user with id {}: {}", userId, userDto);

        Long id = userDto.getId();
        if (id == null) {
            ApiError error = new ApiError("User id is null");
            return ResponseEntity.badRequest().body(error);
        }

        if (!id.equals(userId)) {
            ApiError error = new ApiError("User id and URL id don't match");
            return ResponseEntity.badRequest().body(error);
        }

        UserDto updated = userService.update(userDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
