package com.mikhailkarpov.vidly.vidlyapi.domain.repo;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsUserByEmail(String email);

    Optional<User> findByEmail(String email);
}
