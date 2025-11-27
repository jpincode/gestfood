package com.gestfood.gestfood.business.dto.product;

import com.gestfood.gestfood.model.entity.Product;

public record ProductUpdateDTO(Long id, String name, Double price, String description) {
    public ProductUpdateDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getDescription());
    }
}
