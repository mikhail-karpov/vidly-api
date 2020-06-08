package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AccountDetails;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AccountRegistrationRequest;

public interface UserService {

    AccountDetails register(AccountRegistrationRequest request) throws UserAlreadyExistsException;
}
