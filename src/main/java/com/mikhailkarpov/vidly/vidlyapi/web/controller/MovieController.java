package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.service.MovieService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies")
@Slf4j
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll(@RequestParam Optional<Long> genreId) {
        List<MovieDto> movies;

        if (genreId.isPresent()) {
            Long id = genreId.get();
            log.debug("Request for movies with genre_id {}", id);
            movies = movieService.findAllByGenreId(id);

        } else {
            log.debug("Request for all movies");
            movies = movieService.findAll();
        }

        return ResponseEntity.ok(movies);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        log.debug("Request for movie with id {}", id);
        MovieDto movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> create(@Valid @RequestBody MovieDto movieDto) {
        log.debug("Request for creating new movie: {}", movieDto);
        MovieDto responseBody = movieService.create(movieDto);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody MovieDto movieDto) {
        log.debug("Request for updating movie: {}", movieDto);

        Long movieId = movieDto.getId();
        if (movieId == null) {
            return ResponseEntity.badRequest().body("Movie id is null");
        }
        else if (!movieId.equals(id))
            return ResponseEntity.badRequest().body("Movie id and URI id don't match");

        MovieDto update = movieService.update(movieDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.debug("Request for deleting movie with id {}", id);
        movieService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
