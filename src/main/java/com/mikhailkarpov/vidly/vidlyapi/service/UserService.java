package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.RegistrationRequest;

public interface UserService {

    UserDto register(RegistrationRequest request) throws UserAlreadyExistsException;
}
