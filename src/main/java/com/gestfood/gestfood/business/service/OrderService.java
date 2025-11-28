package com.gestfood.gestfood.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.order.OrderRequestDTO;
import com.gestfood.gestfood.business.dto.order.OrderResponseDTO;
import com.gestfood.gestfood.business.dto.order.OrderUpdateDTO;
import com.gestfood.gestfood.business.exception.BadRequestException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.model.entity.Order;
import com.gestfood.gestfood.model.entity.Product;
import com.gestfood.gestfood.model.repository.OrderRepository;
import com.gestfood.gestfood.model.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService implements InnerDefaultCrud<OrderRequestDTO, OrderResponseDTO, OrderUpdateDTO> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(OrderRequestDTO dto) {
        Order order = new Order(dto);

        BigDecimal total = dto.products().stream()
                .map(Product::getId)
                .map(idFind -> productRepository.findById(idFind)
                        .orElseThrow(() -> new EntityNotFoundException("Produto com ID: " + idFind + " não encontrado.")))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);
        order.setClient(dto.client());
        order.setProducts(new ArrayList<>(dto.products()));

        validateEntity(order);

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void update(Long id, OrderUpdateDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));

        BigDecimal recalculatedTotal = dto.products().stream()
                .map(Product::getId)
                .map(idFind -> productRepository.findById(idFind)
                        .orElseThrow(() -> new EntityNotFoundException("Produto com ID: " + idFind + " não encontrado.")))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setDescription(dto.description());
        order.setStatus(dto.status());
        order.setTotalAmount(recalculatedTotal);
        order.setClient(dto.client());
        order.setProducts(new ArrayList<>(dto.products()));

        validateEntity(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validationService.validateId(id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));

        orderRepository.delete(order);
    }

    @Override
    public List<OrderResponseDTO> read() {
        return orderRepository.findAll()
                .stream()
                .map(OrderResponseDTO::new)
                .toList();
    }

    @Override
    public OrderResponseDTO read(Long id) {
        validationService.validateId(id);
        return orderRepository.findById(id)
                .map(OrderResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));
    }

    private void validateEntity(Order entity) {
        if(entity.getDescription() == null || entity.getDescription().isBlank()) {
            throw new BadRequestException("A descrição do pedido não pode ser nula/vazia.");
        }
        if(entity.getStatus() == null || entity.getStatus().isBlank()) {
            throw new BadRequestException("O status do pedido não pode ser nulo/vazio.");
        }
        if(entity.getClient() == null || entity.getClient().getId() <= 0) {
            throw new BadRequestException("O ID do cliente não pode ser nulo/menor ou igual a zero.");
        }
    }
}
