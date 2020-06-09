package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Movie;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotEmpty(message = "Title is required")
    private String title;

    @JsonProperty(value = "genre")
    @NotNull(message = "Genre is required")
    private GenreDto genreDto;

    @NotNull(message = "Numbers in stock is required")
    @Size(max = 100, message = "Must be from 0 to 100")
    private Integer numberInStock;

    @NotNull(message = "Daily rental rate is required")
    @Size(max = 10, message = "Must be from 0 to 10")
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
