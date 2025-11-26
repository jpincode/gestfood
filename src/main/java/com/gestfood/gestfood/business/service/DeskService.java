package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.DeskDTO;
import com.gestfood.gestfood.business.exception.EntityConflictException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.business.exception.InternalServerErrorException;
import com.gestfood.gestfood.model.entity.Desk;
import com.gestfood.gestfood.model.repository.DeskRepository;

import jakarta.transaction.Transactional;

@Service
public class DeskService implements InnerDefaultCrud<DeskDTO> {
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private ConverterService converterService;

    @Override
    public void create(DeskDTO deskDTO) {
        if (deskDTO.getSeats() <= 0) {
            throw new InternalServerErrorException("A mesa deve ter um número positivo de assentos.");
        }
        if (deskDTO.getSeats() > 15) {
            throw new InternalServerErrorException("O número de lugares deve ser inferior a 15.");
        }
        Desk desk = converterService.dtoToDesk(deskDTO);
        Optional<Desk> deskExists = deskRepository.findById(desk.getId());
        if (deskExists.isPresent()) {
            throw new EntityConflictException("Já existe uma mesa com o ID: " + desk.getId());
        }
        deskRepository.save(desk);
    }

    @Override
    @Transactional
    public void update(DeskDTO deskDTO) {
        if (deskDTO.getSeats() <= 0) {
            throw new InternalServerErrorException("A mesa deve ter um número positivo de assentos.");
        }
        if (deskDTO.getSeats() > 15) {
            throw new InternalServerErrorException("O número de lugares deve ser inferior a 15.");
        }
        Desk desk = converterService.dtoToDesk(deskDTO);
        Optional<Desk> existingDesk = deskRepository.findById(desk.getId());

        if (existingDesk.isEmpty()) {
            throw new EntityNotFoundException("A mesa não foi encontrada.");
        }

        desk.setId(existingDesk.get().getId());
        deskRepository.save(desk);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new InternalServerErrorException("O ID da mesa não pode ser nulo ou menor ou igual a zero");
        }
        Optional<Desk> existingDesk = deskRepository.findById(id);
        if (existingDesk.isEmpty()) {
            throw new EntityNotFoundException("A mesa não foi encontrada.");
        }
        deskRepository.deleteById(id);
    }

    @Override
    public List<DeskDTO> read() {
        List<Desk> desks = deskRepository.findAll();
        List<DeskDTO> deskDTOs = new ArrayList<>();

        if (desks.isEmpty()) {
            throw new EntityNotFoundException("Não existem mesas cadastradas.");
        }

        for (Desk desk : desks) {
            deskDTOs.add(converterService.deskToDto(desk));
        }

        return deskDTOs;
    }

    @Override
    public DeskDTO read(Long id) {
        if (id == null) {
            throw new InternalServerErrorException("O ID da mesa não pode ser nulo.");
        }
        Optional<Desk> desk = deskRepository.findById(id);
        if (desk.isEmpty()) {
            throw new EntityNotFoundException("A mesa não foi encontrada.");
        }
        DeskDTO deskDTO = converterService.deskToDto(desk.get());
        return deskDTO;
    }
}
