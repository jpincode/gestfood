package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.DeskDTO;
import com.gestfood.gestfood.business.exception.InternalServerErrorException;
import com.gestfood.gestfood.business.exception.NoContentException;
import com.gestfood.gestfood.business.exception.ResourceNotFoundException;
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
                throw new InternalServerErrorException("A mesa deve ter um número positivo de assentos.");
            }
            if(deskDTO.getSeats() > 15) {
                throw new InternalServerErrorException("O número de lugares deve ser inferior a 15.");
            }
            Desk desk = converterService.dtoToDesk(deskDTO);
            if (desk == null) {
                throw new InternalServerErrorException("Não foi possível converter o DTO de mesa em entidade.");
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
                throw new InternalServerErrorException("A mesa deve ter um número positivo de assentos.");
            }
            if(deskDTO.getSeats() > 15) {
                throw new InternalServerErrorException("O número de lugares deve ser inferior a 15.");
            }
            Desk desk = converterService.dtoToDesk(deskDTO);
            Optional<Desk> existingDesk = deskRepository.findById(desk.getId());
            
            if (existingDesk.isEmpty()) {
                throw new ResourceNotFoundException("Mesa", desk.getId());
            }

            desk.setId(existingDesk.get().getId());
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
                throw new InternalServerErrorException("O ID da mesa não pode ser nulo ou menor ou igual a zero");
            }
            Optional<Desk> existingDesk = deskRepository.findById(id);
            if (existingDesk.isEmpty()) {
                throw new ResourceNotFoundException("Mesa", id);
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
                throw new NoContentException("Mesa");
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
                throw new InternalServerErrorException("O ID da mesa não pode ser nulo.");
            }
            Optional<Desk> desk = deskRepository.findById(id);
            if (desk.isEmpty()) {
                throw new ResourceNotFoundException("Mesa", id);
            }
            DeskDTO deskDTO = converterService.deskToDto(desk.get());
            return deskDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}
