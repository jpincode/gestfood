package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.OrderDTO;
import com.gestfood.gestfood.business.exception.EntityConflictException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.model.entity.Order;
import com.gestfood.gestfood.model.repository.OrderRepository;

@Service
public class OrderService implements InnerDefaultCrud<OrderDTO> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ConverterService converterService;

    @Override
    public void create(OrderDTO dto) {
        Order order = converterService.dtoToOrder(dto);
        Optional<Order> orderExists = orderRepository.findById(order.getId());
        
        if (orderExists.isPresent()) {
            throw new EntityConflictException("Já existe um pedido com o ID: " + order.getId());
        }
        order.calculateTotalAmount();
        orderRepository.save(order);
    }

    @Override
    public void update(OrderDTO dto) {
        Optional<Order> optionalOrder = orderRepository.findById(dto.getId());
        if (!optionalOrder.isPresent()) {
            throw new EntityNotFoundException("O pedido não foi encontrado.");
        }
        
        Order orderToUpdate = optionalOrder.get();
        Order updatedOrder = converterService.dtoToOrder(dto);
        
        updatedOrder.setId(orderToUpdate.getId());
        orderRepository.save(updatedOrder);
    }

    @Override
    public void delete(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Não foi possivel encontrar o pedido com o ID: " + id);
        }
    }

    @Override
    public List<OrderDTO> read() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();

        if (orders.isEmpty()) {
            throw new EntityNotFoundException("Não existem pedidos cadastrados.");
        }
        
        for (Order order : orders) {
            OrderDTO dto = converterService.orderToDto(order);
            orderDTOs.add(dto);
        } 
        return orderDTOs;
    }

    @Override
    public OrderDTO read(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return converterService.orderToDto(optionalOrder.get());
        } else {
            throw new EntityNotFoundException("O pedido com ID: " + id + " não foi encontrado.");
        }
    }
}
