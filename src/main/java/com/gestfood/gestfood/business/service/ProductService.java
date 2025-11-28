package com.gestfood.gestfood.business.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.product.ProductRequestDTO;
import com.gestfood.gestfood.business.dto.product.ProductResponseDTO;
import com.gestfood.gestfood.business.dto.product.ProductUpdateDTO;
import com.gestfood.gestfood.business.exception.BadRequestException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.model.entity.Product;
import com.gestfood.gestfood.model.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService implements InnerDefaultCrud<ProductRequestDTO, ProductResponseDTO, ProductUpdateDTO> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ValidationService validationService;

    @Override
    public void create(ProductRequestDTO dto) {
        Product product = new Product(dto);
        validateEntity(product);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void update(Long id, ProductUpdateDTO dto) {
        validationService.validateId(id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        validateEntity(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validationService.validateId(id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

        productRepository.delete(product);
    }

    @Override
    public List<ProductResponseDTO> read() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    @Override
    public ProductResponseDTO read(Long id) {
        validationService.validateId(id);
        return productRepository.findById(id)
                .map(ProductResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    private void validateEntity(Product entity) {
        if (entity.getDescription() == null || entity.getDescription().isBlank()) {
            throw new BadRequestException("A descrição do produto não pode ser nula/vazia.");
        }
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new BadRequestException("O nome do produto não pode ser nulo/vazio.");
        }
        if (entity.getPrice() == null || entity.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("O preço do produto não pode ser nulo/menor ou igual a zero.");
        }
    }
}
