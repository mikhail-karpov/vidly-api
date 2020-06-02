package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Movie;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {

    private Long id;
    private String title;

    @JsonProperty(value = "genre")
    private GenreDto genreDto;
    private Integer numberInStock;
    private Double dailyRentalRate;

    public static MovieDto convertToDTO(Movie movie) {
        return MovieDto
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genreDto(GenreDto.fromEntity(movie.getGenre()))
                .dailyRentalRate(movie.getDailyRentalRate())
                .numberInStock(movie.getNumberInStock())
                .build();
    }
}
