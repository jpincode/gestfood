package com.gestfood.gestfood.business.service;

import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.exception.BadRequestException;

@Service
public class ValidationService {
    public void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new BadRequestException("Id inválido.");
        }
    }
}
