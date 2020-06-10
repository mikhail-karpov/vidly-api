package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserRoleDto;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    UserRoleDto create(UserRoleDto role);

    void deleteById(Long id);

    List<UserRoleDto> findAll();

    Optional<UserRoleDto> findById(Long id);

    UserRoleDto update(UserRoleDto role);
}
