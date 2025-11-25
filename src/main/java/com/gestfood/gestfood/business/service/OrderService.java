package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.OrderDTO;
import com.gestfood.gestfood.business.exception.NoContentException;
import com.gestfood.gestfood.business.exception.ResourceConflictException;
import com.gestfood.gestfood.business.exception.ResourceNotFoundException;
import com.gestfood.gestfood.model.entity.Order;
import com.gestfood.gestfood.model.repository.OrderRepository;

@Service
public class OrderService implements InnerDefaultCRUD<OrderDTO> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ConverterService converterService;

    @Override
    public void create(OrderDTO dto) {
        try {
            Order order = converterService.dtoToOrder(dto);
            Optional<Order> orderExists = orderRepository.findById(order.getId());
            
            if (orderExists.isPresent()) {
                throw new ResourceConflictException("Pedido", order.getId());
            }

            orderRepository.save(order);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(OrderDTO dto) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(dto.getId());
            if (!optionalOrder.isPresent()) {
                throw new ResourceNotFoundException("Pedido", dto.getId());
            }
            
            Order orderToUpdate = optionalOrder.get();
            Order updatedOrder = converterService.dtoToOrder(dto);
            
            updatedOrder.setId(orderToUpdate.getId());
            orderRepository.save(updatedOrder);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            if (order.isPresent()) {
                orderRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("Pedido", id);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<OrderDTO> read() {
        try {
            List<Order> orders = orderRepository.findAll();
            List<OrderDTO> orderDTOs = new ArrayList<>();

            if (orders == null) {
                throw new NoContentException("pedido");
            }
            
            for (Order order : orders) {
                OrderDTO dto = converterService.orderToDto(order);
                orderDTOs.add(dto);
            } 
            return orderDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public OrderDTO read(Long id) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(id);
            if (optionalOrder.isPresent()) {
                return converterService.orderToDto(optionalOrder.get());
            } else {
                throw new ResourceNotFoundException("Order", id);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
