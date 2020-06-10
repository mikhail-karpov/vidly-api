package com.mikhailkarpov.vidly.vidlyapi.service.impl;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.GenreEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.GenreRepository;
import com.mikhailkarpov.vidly.vidlyapi.service.GenreService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.GenreDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDto> findAll() {
        List<GenreEntity> genreEntities = new ArrayList<>();
        genreRepository.findAll().forEach(genreEntities::add);
        return genreEntities
                .stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(GenreDto::getName))
                .collect(Collectors.toList());
    }

    private GenreDto convertToDto(GenreEntity genreEntity) {
        return new GenreDto(genreEntity.getId(), genreEntity.getName());
    }
}
