package com.gestfood.gestfood.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.client.ClientRequestDTO;
import com.gestfood.gestfood.business.dto.client.ClientResponseDTO;
import com.gestfood.gestfood.business.dto.client.ClientUpdateDTO;
import com.gestfood.gestfood.model.entity.Client;
import com.gestfood.gestfood.model.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ValidationService validationService;

    public void create(ClientRequestDTO dto) {
        validationService.validatePassword(dto.password());
        validationService.validateEmail(dto.email());
        validationService.validateCpf(dto.cpf());

        Client client = new Client(dto);
        clientRepository.save(client);
    }

    @Transactional
    public void update(Long id, ClientUpdateDTO dto) {
        validationService.validateId(id);
        validationService.validatePassword(dto.password());
        validationService.validateEmail(dto.email());
        validationService.validateCpf(dto.cpf());

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        client.setName(dto.name());
        client.setCpf(dto.cpf());
        client.setPassword(dto.password());
        client.setEmail(dto.email());
        client.setAddress(dto.address());
        client.setPhoneNumber(dto.phoneNumber());
    }

    @Transactional
    public void delete(Long id) {
        validationService.validateId(id);
        
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        clientRepository.delete(client);
    }

    public List<ClientResponseDTO> read() {
        return clientRepository.findAll()
                .stream()
                .map(ClientResponseDTO::new)
                .toList();
    }

    public ClientResponseDTO read(Long id) {
        return clientRepository.findById(id)
                .map(ClientResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
    }
}
