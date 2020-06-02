package com.mikhailkarpov.vidly.vidlyapi.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for JPA
@Getter
@Setter
public class Genre extends BaseEntity {

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
