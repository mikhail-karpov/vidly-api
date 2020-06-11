package com.mikhailkarpov.vidly.vidlyapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movies")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for JPA
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @NotNull
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    @NotNull
    private GenreEntity genre;

    @Column(name= "in_stock", nullable = false)
    @NotNull
    private Integer numberInStock;

    @Column(name= "daily_rate", nullable = false)
    @NotNull
    private Double dailyRentalRate;
}
