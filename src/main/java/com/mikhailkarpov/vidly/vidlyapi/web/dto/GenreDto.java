package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Genre;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto {

    private Long id;
    private String name;

    public static GenreDto fromEntity(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

}
