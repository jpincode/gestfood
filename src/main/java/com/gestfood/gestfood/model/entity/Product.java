package com.gestfood.gestfood.model.entity;

import com.gestfood.gestfood.business.dto.product.ProductRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double price;
    private String description;

    public Product(ProductRequestDTO productRequestDTO) {
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
        this.description = productRequestDTO.description();
    }
}
