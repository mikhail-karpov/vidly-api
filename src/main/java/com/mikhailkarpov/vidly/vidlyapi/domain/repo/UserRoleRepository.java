package com.mikhailkarpov.vidly.vidlyapi.domain.repo;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByName(String name);
}
