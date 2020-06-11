package com.mikhailkarpov.vidly.vidlyapi.domain.repo;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {

    List<MovieEntity> findAllByGenre_Id(Long id);

}
