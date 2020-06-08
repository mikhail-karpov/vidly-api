package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.User;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(String email, String password) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmail(email))
            throw new UserAlreadyExistsException();

        User user = userRepository.save(new User(email, password));
        return UserDto.fromEntity(user);
    }
}
