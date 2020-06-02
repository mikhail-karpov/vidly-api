package com.mikhailkarpov.vidly.vidlyapi.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for JPA
@Getter
@Setter
@ToString
public class Movie extends BaseEntity {

    private String title;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private Integer numberInStock;
    private Double dailyRentalRate;

    public Movie(String title, Genre genre, Integer numberInStock, Double dailyRentalRate) {
        this.title = title;
        this.genre = genre;
        this.numberInStock = numberInStock;
        this.dailyRentalRate = dailyRentalRate;
    }
}
