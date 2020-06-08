package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.User;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto register(String email, String password);

    List<UserDto> findAll();

    Optional<UserDto> findByEmail(String email);

}
