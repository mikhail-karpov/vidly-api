package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.web.dto.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto create(MovieDto movieDto);

    void deleteById(Long id);

    List<MovieDto> findAll();

    List<MovieDto> findAllByGenreId(Long genreId);

    MovieDto findById(Long id);

    MovieDto update(MovieDto movieDto);

}
