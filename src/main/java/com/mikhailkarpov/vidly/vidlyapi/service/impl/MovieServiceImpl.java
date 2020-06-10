package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Genre;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.MovieEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.GenreRepository;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.MovieRepository;
import com.mikhailkarpov.vidly.vidlyapi.exception.GenreNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.exception.MovieNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.exception.MyBadRequestException;
import com.mikhailkarpov.vidly.vidlyapi.service.MovieService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public MovieDto create(MovieDto movieDto) {
        log.info("Creating movie {}", movieDto);

        if (movieDto.getId() != null)
            throw new MyBadRequestException("Movie id already exists");

        Genre genre = getGenre(movieDto);

        MovieEntity movieEntity = new MovieEntity(movieDto.getTitle(), genre, movieDto.getNumberInStock(), movieDto.getDailyRentalRate());
        MovieEntity saved = movieRepository.save(movieEntity);

        return MovieDto.convertToDTO(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting movie with id = {}", id);

        MovieEntity movieEntity = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        movieRepository.delete(movieEntity);
    }

    @Override
    @Transactional
    public List<MovieDto> findAll() {
        List<MovieEntity> movieEntities = new ArrayList<>();
        movieRepository.findAll().forEach(movieEntities::add);
        return Collections.unmodifiableList(movieEntities.stream().map(MovieDto::convertToDTO).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public MovieDto findById(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return MovieDto.convertToDTO(movieEntity);
    }

    @Override
    @Transactional
    public MovieDto update(MovieDto movieDto) {
        log.info("Updating movie: {}", movieDto);

        Long movieId = movieDto.getId();
        MovieEntity movieEntity = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));

        movieEntity.setGenre(getGenre(movieDto));
        movieEntity.setTitle(movieDto.getTitle());
        movieEntity.setNumberInStock(movieDto.getNumberInStock());
        movieEntity.setDailyRentalRate(movieDto.getDailyRentalRate());

        MovieEntity save = movieRepository.save(movieEntity);
        return MovieDto.convertToDTO(save);
    }

    private Genre getGenre(MovieDto movieDto) {
        Long genreId = movieDto.getGenreDto().getId();

        if (genreId == null) {
            throw new MyBadRequestException("Genre id is null");
        }

        return genreRepository
                .findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }
}
