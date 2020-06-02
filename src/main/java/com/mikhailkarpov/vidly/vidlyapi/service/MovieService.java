package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.exception.MovieNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();

    MovieDto findById(Long id) throws MovieNotFoundException;

    MovieDto create(MovieDto movieDto);

    void update(MovieDto movieDto);

    void delete(Long id) throws MovieNotFoundException;
}
