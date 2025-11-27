package com.gestfood.gestfood.business.dto.desk;

import com.gestfood.gestfood.model.entity.Desk;

public record DeskRequestDTO(int seats) {
    public DeskRequestDTO(Desk desk) {
        this(desk.getSeats());
    }
}
