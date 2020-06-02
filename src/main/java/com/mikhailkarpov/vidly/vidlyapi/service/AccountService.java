package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.exception.AccountAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AccountDetails;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AccountRegistrationRequest;

public interface AccountService {

    AccountDetails register(AccountRegistrationRequest request) throws AccountAlreadyExistsException;
}
