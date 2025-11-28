package com.gestfood.gestfood.business.dto.client;

import com.gestfood.gestfood.model.entity.Client;

public record ClientRequestDTO(String name, String email, String cpf, String password, String phoneNumber, String address) {
    public ClientRequestDTO(Client c) {
        this(c.getName(), c.getEmail(), c.getCpf(), c.getPassword(), c.getPhoneNumber(), c.getAddress());
    }
}
