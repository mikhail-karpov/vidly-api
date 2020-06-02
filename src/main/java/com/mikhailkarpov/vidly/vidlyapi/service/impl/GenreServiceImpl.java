package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Genre;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.GenreRepository;
import com.mikhailkarpov.vidly.vidlyapi.service.GenreService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.GenreDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDto> findAll() {
        List<Genre> genres = new ArrayList<>();
        genreRepository.findAll().forEach(genres::add);
        return genres
                .stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(GenreDto::getName))
                .collect(Collectors.toList());
    }

    private GenreDto convertToDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
