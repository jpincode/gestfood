package com.gestfood.gestfood.models.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
public class Board {
    @Id
    private Long id;

    @Nonnull
    private int seats;

    public Board() {
    }

    public Board(Long id, int seats) {
        this.id = id;
        this.seats = seats;
    }
}
