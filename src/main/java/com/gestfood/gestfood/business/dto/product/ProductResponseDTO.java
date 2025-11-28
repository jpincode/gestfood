package com.gestfood.gestfood.business.dto.product;

import java.math.BigDecimal;

import com.gestfood.gestfood.model.entity.Product;

public record ProductResponseDTO(Long id, String name, BigDecimal price, String description) {
    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getDescription());
    }
}
