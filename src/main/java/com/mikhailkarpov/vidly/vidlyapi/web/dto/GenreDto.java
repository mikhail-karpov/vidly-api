package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Genre;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto {

    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 16, message = "Must be from 2 to 16 characters long")
    private String name;

    public static GenreDto fromEntity(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

}
