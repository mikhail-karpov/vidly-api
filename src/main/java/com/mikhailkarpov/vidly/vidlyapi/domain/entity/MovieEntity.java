package com.mikhailkarpov.vidly.vidlyapi.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for JPA
@Getter
@Setter
@ToString
public class MovieEntity extends BaseEntity {

    private String title;

    @OneToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genreEntity;

    private Integer numberInStock;
    private Double dailyRentalRate;

    public MovieEntity(String title, GenreEntity genreEntity, Integer numberInStock, Double dailyRentalRate) {
        this.title = title;
        this.genreEntity = genreEntity;
        this.numberInStock = numberInStock;
        this.dailyRentalRate = dailyRentalRate;
    }
}
