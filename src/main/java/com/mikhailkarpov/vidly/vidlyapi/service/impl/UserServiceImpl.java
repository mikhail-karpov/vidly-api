package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(String email, String password) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmail(email))
            throw new UserAlreadyExistsException();

        String encodedPassword = passwordEncoder.encode(password);
        UserEntity userEntity = userRepository.save(new UserEntity(email, encodedPassword));

        return UserDto.fromEntity(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();

        userRepository.findAll().forEach(userEntity -> users.add(UserDto.fromEntity(userEntity)));

        return Collections.unmodifiableList(users);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDto::fromEntity);
    }
}
