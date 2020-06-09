package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Genre;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.GenreRepository;
import com.mikhailkarpov.vidly.vidlyapi.service.GenreService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.GenreDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("genres")
public class GenreController {

    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDto> findAll() {
        return genreService.findAll();
    }
}
