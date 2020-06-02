package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.service.MovieService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.MovieDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> findAll() {
        return movieService.findAll();
    }

    @GetMapping("{id}")
    public MovieDto findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public MovieDto create(@RequestBody MovieDto movieDto) {
        return movieService.create(movieDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        if (!movieDto.getId().equals(id))
            return ResponseEntity.badRequest().body("Movie id and URI id don't match");

        movieService.update(movieDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }
}
