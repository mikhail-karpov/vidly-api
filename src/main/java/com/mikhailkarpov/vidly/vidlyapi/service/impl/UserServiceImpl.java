package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRoleEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRoleRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRoleEntity.DEFAULT_ROLE;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(String email, String password) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmail(email))
            throw new UserAlreadyExistsException();

        String encodedPassword = passwordEncoder.encode(password);
        UserRoleEntity roleEntity = userRoleRepository
                .findByName(DEFAULT_ROLE)
                .orElse(new UserRoleEntity(DEFAULT_ROLE));

        UserEntity userEntity = new UserEntity(email, encodedPassword, Collections.singleton(roleEntity));

        return UserDto.fromEntity(userRepository.save(userEntity));
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
