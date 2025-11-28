package com.gestfood.gestfood.business.dto.client;

import com.gestfood.gestfood.model.entity.Client;

public record ClientUpdateDTO(Long id, String name, String email, String cpf, String password, String phoneNumber, String address) {
    public ClientUpdateDTO(Client c) {
        this(c.getId(), c.getName(), c.getEmail(), c.getCpf(), c.getPassword(), c.getPhoneNumber(), c.getAddress());
    }
}
