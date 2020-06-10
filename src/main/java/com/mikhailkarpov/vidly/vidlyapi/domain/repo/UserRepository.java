package com.mikhailkarpov.vidly.vidlyapi.domain.repo;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    boolean existsUserByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
