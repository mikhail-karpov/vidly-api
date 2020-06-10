package com.mikhailkarpov.vidly.vidlyapi.domain.repo;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.GenreEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<GenreEntity, Long> {
}
