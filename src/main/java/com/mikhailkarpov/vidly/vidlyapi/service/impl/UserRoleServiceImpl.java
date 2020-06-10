package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRoleEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRoleRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.MyBadRequestException;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserRoleNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.service.UserRoleService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserRoleDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository roleRepository;

    public UserRoleServiceImpl(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRoleDto create(UserRoleDto dto) {
        UserRoleEntity entity = roleRepository.save(new UserRoleEntity(dto.getName()));
        return UserRoleDto.fromEntity(entity);
    }

    @Override
    public void deleteById(Long id) {
        UserRoleEntity entity = roleRepository.findById(id).orElseThrow(() -> new UserRoleNotFoundException(id));
        roleRepository.delete(entity);
    }

    @Override
    public List<UserRoleDto> findAll() {
        List<UserRoleDto> dtoList = new ArrayList<>();
        roleRepository.findAll().forEach(roleEntity -> dtoList.add(UserRoleDto.fromEntity(roleEntity)));
        return Collections.unmodifiableList(dtoList);
    }

    @Override
    public Optional<UserRoleDto> findById(Long id) {
        return roleRepository.findById(id).map(UserRoleDto::fromEntity);
    }

    @Override
    public UserRoleDto update(UserRoleDto role) {
        Long id = role.getId();
        if (id == null)
            throw new MyBadRequestException("Role id is null");

        UserRoleEntity entity = roleRepository.findById(id).orElseThrow(() -> new UserRoleNotFoundException(id));
        entity.setName(role.getName());

        return role;
    }
}
