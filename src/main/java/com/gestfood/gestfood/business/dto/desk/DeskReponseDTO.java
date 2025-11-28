package com.gestfood.gestfood.business.dto.desk;

import com.gestfood.gestfood.model.entity.Desk;

public record DeskReponseDTO(Long id, int seats) {
    public DeskReponseDTO(Desk desk) {
        this(desk.getId(), desk.getSeats());
    }
}
