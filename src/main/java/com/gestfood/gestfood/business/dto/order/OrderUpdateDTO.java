package com.gestfood.gestfood.business.dto.order;

import java.math.BigDecimal;
import java.util.List;

import com.gestfood.gestfood.model.entity.Order;

public record OrderUpdateDTO(Long id, String description, BigDecimal totalAmount, String status, Long clientId, List<Long> productIds) {
    public OrderUpdateDTO(Order o) {
        this(o.getId(), o.getDescription(), o.getTotalAmount(), o.getStatus(), o.getClientId(), o.getProductIds().stream().toList());
    }
}
