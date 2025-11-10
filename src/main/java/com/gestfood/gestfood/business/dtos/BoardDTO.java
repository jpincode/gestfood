package com.gestfood.gestfood.business.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private Long id;
    
    private int seats;

    public BoardDTO(Long id, int seats) {
        this.id = id;
        this.seats = seats;
    } 
}
