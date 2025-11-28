package com.gestfood.gestfood.business.dto.order;

import java.math.BigDecimal;
import java.util.List;

import com.gestfood.gestfood.model.entity.Client;
import com.gestfood.gestfood.model.entity.Order;
import com.gestfood.gestfood.model.entity.Product;

public record OrderResponseDTO(Long id, String description, BigDecimal totalAmount, String status, Client client, List<Product> products) {
    public OrderResponseDTO(Order o) {
        this(o.getId(), o.getDescription(), o.getTotalAmount(), o.getStatus(), o.getClient(), o.getProducts().stream().toList());
    }
}
