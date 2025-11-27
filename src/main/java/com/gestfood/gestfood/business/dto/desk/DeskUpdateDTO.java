package com.gestfood.gestfood.business.dto.desk;

import com.gestfood.gestfood.model.entity.Desk;

public record DeskUpdateDTO(Long id, int seats) {
    public DeskUpdateDTO(Desk desk) {
        this(desk.getId(), desk.getSeats());
    }
}
