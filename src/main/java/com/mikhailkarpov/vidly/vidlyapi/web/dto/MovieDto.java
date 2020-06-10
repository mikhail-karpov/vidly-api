package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.MovieEntity;
import lombok.*;

import javax.validation.constraints.*;

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
    @Size(min = 2, max = 100, message = "Title must be from 2 to 100 characters long")
    private String title;

    @JsonProperty(value = "genre")
    @NotNull(message = "Genre is required")
    private GenreDto genreDto;

    @NotNull(message = "Numbers in stock is required")
    @Min(value = 0, message = "Must be from 0 to 100")
    @Max(value = 100, message = "Must be from 0 to 100")
    private Integer numberInStock;

    @NotNull(message = "Daily rental rate is required")
    @Min(value = 0, message = "Must be from 0 to 10")
    @Max(value = 10, message = "Must be from 0 to 10")
    private Double dailyRentalRate;

    public static MovieDto convertToDTO(MovieEntity movieEntity) {
        return MovieDto
                .builder()
                .id(movieEntity.getId())
                .title(movieEntity.getTitle())
                .genreDto(GenreDto.fromEntity(movieEntity.getGenre()))
                .dailyRentalRate(movieEntity.getDailyRentalRate())
                .numberInStock(movieEntity.getNumberInStock())
                .build();
    }
}
