package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRole;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto create(String email, String password, Set<UserRole> roles);

    void delete(Long userId);

    List<UserDto> findAll();

    UserDto findByEmail(String email);

    UserDto findById(Long userId);

    UserDto update(UserDto userDto);
}
