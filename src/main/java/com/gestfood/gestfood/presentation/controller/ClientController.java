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

import com.gestfood.gestfood.business.dto.client.ClientRequestDTO;
import com.gestfood.gestfood.business.dto.client.ClientResponseDTO;
import com.gestfood.gestfood.business.dto.client.ClientUpdateDTO;
import com.gestfood.gestfood.business.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createDesk(@RequestBody ClientRequestDTO dto) {
        clientService.create(dto);
        return ResponseEntity.ok().body("Cliente cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDesk(@PathVariable Long id, @RequestBody ClientUpdateDTO dto) {
        clientService.update(id, dto);
        return ResponseEntity.ok().body("Cliente atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDesk(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().body("Cliente deletado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllDesks() {
        return ResponseEntity.ok().body(clientService.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getDeskById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.read(id));
    }
}
