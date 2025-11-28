package com.gestfood.gestfood.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestfood.gestfood.business.dto.order.OrderRequestDTO;
import com.gestfood.gestfood.business.dto.order.OrderResponseDTO;
import com.gestfood.gestfood.business.dto.order.OrderUpdateDTO;
import com.gestfood.gestfood.business.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderDTO) {
        orderService.create(orderDTO);
        return ResponseEntity.ok().body("Pedido cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody OrderUpdateDTO orderDTO) {
        orderService.update(id, orderDTO);
        return ResponseEntity.ok().body("Pedido atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().body("Pedido deletado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.read(id));
    }
}
