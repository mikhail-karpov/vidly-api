package com.mikhailkarpov.vidly.vidlyapi.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Movie extends BaseEntity {

    private String title;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private Integer numberInStock;
    private Double dailyRentalRate;

    public Movie(String title, Integer numberInStock, Double dailyRentalRate) {
        this.title = title;
//        this.genre = genre;
        this.numberInStock = numberInStock;
        this.dailyRentalRate = dailyRentalRate;
    }
}
