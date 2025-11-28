package com.gestfood.gestfood.business.dto.client;

import com.gestfood.gestfood.model.entity.Client;

public record ClientResponseDTO(Long id, String name, String email, String cpf, String phoneNumber, String address) {
    public ClientResponseDTO(Client c) {
        this(c.getId(), c.getName(), c.getEmail(), c.getCpf(), c.getPhoneNumber(), c.getAddress());
    }
}
