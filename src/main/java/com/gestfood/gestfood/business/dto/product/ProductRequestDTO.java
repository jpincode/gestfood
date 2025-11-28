package com.gestfood.gestfood.business.dto.product;

import java.math.BigDecimal;
import java.util.List;

import com.gestfood.gestfood.model.entity.Product;
import com.gestfood.gestfood.model.entity.ProductImage;

public record ProductRequestDTO(String name, BigDecimal price, String description, List<ProductImage> images) {
    public ProductRequestDTO(Product product) {
        this(product.getName(), product.getPrice(), product.getDescription(), product.getImages());
    }
}
