package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.DeskDTO;
import com.gestfood.gestfood.model.entity.Desk;
import com.gestfood.gestfood.model.repository.DeskRepository;

import jakarta.transaction.Transactional;

@Service
public class DeskService implements InnerDefaultCRUD<DeskDTO> {
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private ConverterService converterService;

    @Override
    public void create(DeskDTO deskDTO) {
        try {
            if(deskDTO.getSeats() <= 0) {
                throw new IllegalArgumentException("Desk must have a positive number of seats.");
            }
            if(deskDTO.getSeats() > 15) {
                throw new IllegalArgumentException("The number of seats must be less than 15.");
            }
            Desk desk = converterService.dtoToDesk(deskDTO);
            if (desk == null) {
                throw new IllegalArgumentException("Could not convert desk DTO to entity");
            }
            deskRepository.save(desk);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(DeskDTO deskDTO) {
        try {
            if(deskDTO.getSeats() <= 0) {
                throw new IllegalArgumentException("Desk must have a positive number of seats.");
            }
            if(deskDTO.getSeats() > 15) {
                throw new IllegalArgumentException("The number of seats must be less than 15.");
            }
            Desk desk = converterService.dtoToDesk(deskDTO);
            if (desk == null) {
                throw new IllegalArgumentException("Could not convert desk DTO to entity");
            }
            deskRepository.save(desk);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("Desk ID cannot be null or less than or equal to zero");
            }
            deskRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<DeskDTO> read() {
        try {
            List<Desk> desks = deskRepository.findAll();
            List<DeskDTO> deskDTOs = new ArrayList<>();

            if(desks.isEmpty()) {
                throw new RuntimeException("There are no tables registered.");
            }

            for (Desk desk : desks) {
                deskDTOs.add(converterService.deskToDto(desk));
            }

            return deskDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DeskDTO read(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Desk ID cannot be null");
            }
            Optional<Desk> desk = deskRepository.findById(id);
            if (desk.isEmpty()) {
                throw new RuntimeException("Desk not found with id: " + id);
            }
            DeskDTO deskDTO = converterService.deskToDto(desk.get());
            return deskDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}
