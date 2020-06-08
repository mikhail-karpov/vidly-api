package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.User;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.AccountAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.service.AccountService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(RegistrationRequest request) throws AccountAlreadyExistsException {
        String email = request.getEmail();
        if (userRepository.findByEmail(email).isPresent())
            throw new AccountAlreadyExistsException("Account already exists with email " + email);

        User user = userRepository.save(new User(email, request.getPassword(), request.getName()));
        return new UserDto(user.getId(), request.getName(), email);
    }
}
