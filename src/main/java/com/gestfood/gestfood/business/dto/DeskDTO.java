package com.gestfood.gestfood.business.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeskDTO {
    private Long id;
    
    private int seats;

    public DeskDTO(Long id, int seats) {
        this.id = id;
        this.seats = seats;
    } 
}
