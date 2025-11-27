package com.gestfood.gestfood.business.dto.product;

import com.gestfood.gestfood.model.entity.Product;

public record ProductResponseDTO(String name, Double price, String description) {
    public ProductResponseDTO(Product product) {
        this(product.getName(), product.getPrice(), product.getDescription());
    }
}
