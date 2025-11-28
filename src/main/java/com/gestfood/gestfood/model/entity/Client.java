package com.gestfood.gestfood.model.entity;

import com.gestfood.gestfood.business.dto.client.ClientRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends AbstractUser {
    private String phoneNumber;
    private String address;

    public Client(ClientRequestDTO dto) {
        this.setName(dto.name());
        this.setCpf(dto.cpf());
        this.setEmail(dto.email());
        this.setPassword(dto.password());
        this.address = dto.address();
        this.phoneNumber = dto.phoneNumber();
    }
}
