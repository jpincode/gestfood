package com.gestfood.gestfood.business.dto.product;

import com.gestfood.gestfood.model.entity.Product;

public record ProductRequestDTO(String name, Double price, String description) {
    public ProductRequestDTO(Product product) {
        this(product.getName(), product.getPrice(), product.getDescription());
    }
}
