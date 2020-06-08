package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {

    UserDto register(String email, String password);

    Optional<UserDto> findByEmail(String email);
}
