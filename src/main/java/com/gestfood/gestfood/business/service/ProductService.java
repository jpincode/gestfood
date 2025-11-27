package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.product.ProductRequestDTO;
import com.gestfood.gestfood.business.dto.product.ProductResponseDTO;
import com.gestfood.gestfood.business.dto.product.ProductUpdateDTO;
import com.gestfood.gestfood.business.exception.EntityConflictException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.business.exception.InternalServerErrorException;
import com.gestfood.gestfood.model.entity.Product;
import com.gestfood.gestfood.model.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService implements InnerDefaultCrud<ProductRequestDTO, ProductResponseDTO, ProductUpdateDTO> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ConverterService converterService;

    @Override
    public void create(ProductDTO productDTO) {
        Product product = converterService.dtoToProduct(productDTO);
        Optional<Product> productExists = productRepository.findById(product.getId());
        if (productExists.isPresent()) {
            throw new EntityConflictException("Já existe um produto com o ID: " + product.getId());
        }
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void update(ProductDTO productDTO) {
        Product product = converterService.dtoToProduct(productDTO);
        Optional<Product> productExists = productRepository.findById(product.getId());
        if (productExists.isEmpty()) {
            throw new EntityNotFoundException("O produto não foi encontrado.");
        }
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new InternalServerErrorException("O ID do produto não pode ser nulo nem menor ou igual a zero.");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> read() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();

        if(products.isEmpty()) {
            throw new EntityNotFoundException("Não existem produtos cadastrados.");
        }
        
        for (Product product : products) {
            productDTOs.add(converterService.productToDto(product));
        }
        return productDTOs;
    }

    @Override
    public ProductDTO read(Long id) {
        if (id == null) {
            throw new InternalServerErrorException("O ID do produto não pode ser nulo.");
        }
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("O produto com ID: " + id + " não foi encontrado.");
        }
        return converterService.productToDto(product.get());
    }
}
