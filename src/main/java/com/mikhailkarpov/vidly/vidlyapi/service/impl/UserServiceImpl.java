package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRole;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.MyBadRequestException;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserDto userDto) {
        String password = userDto.getPassword();
        String matchingPassword = userDto.getMatchingPassword();

        if (!password.equals(matchingPassword))
            throw new MyBadRequestException("Passwords don't match");

        String email = userDto.getEmail();
        String encodedPassword = passwordEncoder.encode(password);
        Set<UserRole> roles = new HashSet<>(userDto.getRoles());

        UserEntity user = userRepository.save(new UserEntity(email, encodedPassword, roles));
        log.info("User created: {}", user);

        return UserDto.fromEntity(user);
    }

    @Override
    public void delete(Long userId) {
        UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
        log.info("User with id {} deleted", userId);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> users.add(UserDto.fromEntity(userEntity)));

        return Collections.unmodifiableList(users);
    }

    @Override
    public UserDto findByEmail(String email) {
        UserEntity userEntity = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + email));
        return UserDto.fromEntity(userEntity);
    }

    @Override
    public UserDto findById(Long userId) {
        return userRepository
                .findById(userId)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public UserDto update(UserDto userDto) {
        Long userId = userDto.getId();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        user.setEmail(userDto.getEmail());
        user.setRoles(new HashSet<>(userDto.getRoles()));

        log.info("User updated: {}", user);
        return userDto;
    }
}
