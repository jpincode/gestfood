package com.gestfood.gestfood.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.desk.DeskReponseDTO;
import com.gestfood.gestfood.business.dto.desk.DeskRequestDTO;
import com.gestfood.gestfood.business.dto.desk.DeskUpdateDTO;
import com.gestfood.gestfood.business.exception.BadRequestException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.model.entity.Desk;
import com.gestfood.gestfood.model.repository.DeskRepository;

import jakarta.transaction.Transactional;

@Service
public class DeskService {
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private ValidationService validationService;

    public void create(DeskRequestDTO dto) {
        validateSeats(dto.seats());
        
        Desk desk = new Desk(dto);
        deskRepository.save(desk);
    }

    @Transactional
    public void update(Long id, DeskUpdateDTO dto) {
        validateSeats(dto.seats());
        validationService.validateId(id);

        Desk desk = deskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("A mesa não foi encontrada."));
        desk.setSeats(dto.seats());
    }

    @Transactional
    public void delete(Long id) {
        validationService.validateId(id);
        
        Desk desk = deskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("A mesa não foi encontrada."));
        
        deskRepository.delete(desk);
    }

    public List<DeskReponseDTO> read() {
        return deskRepository.findAll()
                .stream()
                .map(DeskReponseDTO::new).toList();
    }

    public DeskReponseDTO read(Long id) {
        validationService.validateId(id);

        return deskRepository.findById(id)
                .map(DeskReponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("A mesa não foi encontrada."));
    }

    private void validateSeats(int seats) {
        if (seats <= 0) {
            throw new BadRequestException("A quantidade de assentos não pode ser menor ou igual a zero.");
        }
        if (seats > 15) {
            throw new BadRequestException("A quantidade de assentos não pode ser maior que 15.");
        }
    }
}
