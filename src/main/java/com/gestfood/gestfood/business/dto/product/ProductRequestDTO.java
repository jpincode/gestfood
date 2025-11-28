package com.gestfood.gestfood.business.dto.product;

import java.math.BigDecimal;

import com.gestfood.gestfood.model.entity.Product;

public record ProductRequestDTO(String name, BigDecimal price, String description) {
    public ProductRequestDTO(Product product) {
        this(product.getName(), product.getPrice(), product.getDescription());
    }
}
