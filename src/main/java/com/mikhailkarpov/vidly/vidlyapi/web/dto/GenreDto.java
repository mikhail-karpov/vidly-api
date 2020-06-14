package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.GenreEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GenreDto {

    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 16, message = "Must be from 2 to 16 characters long")
    private String name;

    public static GenreDto fromEntity(GenreEntity entity) {
        GenreDto dto = new GenreDto();

        dto.id = entity.getId();
        dto.name = entity.getName();

        return dto;
    }
}