package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;

public interface UserService {

    UserDto register(String email, String password);
}
