package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Movie;
import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MovieDto {

    private Long id;
    private String title;
    private GenreDto genre;
    private Integer numberInStock;
    private Double dailyRentalRate;

    public static MovieDto fromEntity(Movie movie) {
        return MovieDto
                .builder()
                .id(movie.getId())
                .genre(GenreDto.fromEntity(movie.getGenre()))
                .dailyRentalRate(movie.getDailyRentalRate())
                .numberInStock(movie.getNumberInStock())
                .build();
    }
}
