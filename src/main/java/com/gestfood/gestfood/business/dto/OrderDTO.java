package com.gestfood.gestfood.business.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String description;
    private Double totalAmount;
    private String status;
    private ClientDTO client;
    private List<ProductDTO> products;
}
