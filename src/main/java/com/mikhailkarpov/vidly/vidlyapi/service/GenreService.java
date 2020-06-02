package com.mikhailkarpov.vidly.vidlyapi.service;

import com.mikhailkarpov.vidly.vidlyapi.web.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();
}
