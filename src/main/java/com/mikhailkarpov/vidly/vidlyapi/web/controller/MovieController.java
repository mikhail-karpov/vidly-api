package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.service.MovieService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
@Slf4j
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll() {
        log.info("Request for all movies");
        List<MovieDto> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        log.info("Request for movie by id = {}", id);
        MovieDto movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody MovieDto movieDto) {
        log.info("Request for creating new movie: {}", movieDto);
        MovieDto responseBody = movieService.create(movieDto);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        log.info("Request for updating movie: {}", movieDto);

        Long movieId = movieDto.getId();
        if (movieId == null)
            return ResponseEntity.badRequest().body("Movie id can't be null");
        else if (!movieId.equals(id))
            return ResponseEntity.badRequest().body("Movie id and URI id don't match");

        MovieDto update = movieService.update(movieDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Request for deleting movie by id = {}", id);
        movieService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
