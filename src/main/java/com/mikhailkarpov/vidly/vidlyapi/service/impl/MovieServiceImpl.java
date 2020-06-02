package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Movie;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.MovieRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.MovieNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.service.MovieService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return Collections.unmodifiableList(movies.stream().map(MovieDto::fromEntity).collect(Collectors.toList()));
    }

    @Override
    public MovieDto findById(Long id) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return MovieDto.fromEntity(movie);
    }

    @Override
    public MovieDto create(MovieDto movieDto) {
        return null;
    }

    @Override
    public void update(MovieDto movieDto) {

    }

    @Override
    public void delete(Long id) throws MovieNotFoundException {

    }
}
