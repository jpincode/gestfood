package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.ProductDTO;
import com.gestfood.gestfood.business.exception.InternalServerErrorException;
import com.gestfood.gestfood.business.exception.NoContentException;
import com.gestfood.gestfood.business.exception.ResourceConflictException;
import com.gestfood.gestfood.business.exception.ResourceNotFoundException;
import com.gestfood.gestfood.model.entity.Product;
import com.gestfood.gestfood.model.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService implements InnerDefaultCRUD<ProductDTO> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ConverterService converterService;

    @Override
    public void create(ProductDTO productDTO) {
        try {
            Product product = converterService.dtoToProduct(productDTO);
            Optional<Product> productExists = productRepository.findById(product.getId());
            if (productExists.isPresent()) {
                throw new ResourceConflictException("produto", product.getId());
            }
            productRepository.save(product);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(ProductDTO productDTO) {
        try {
            Product product = converterService.dtoToProduct(productDTO);
            Optional<Product> productExists = productRepository.findById(product.getId());
            if (productExists.isEmpty()) {
                throw new ResourceNotFoundException("produto", product.getId());
            }
            productRepository.save(product);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            if (id == null || id <= 0) {
                throw new InternalServerErrorException("O ID do produto não pode ser nulo nem menor ou igual a zero.");
            }
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ProductDTO> read() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOs = new ArrayList<>();

            if(products.isEmpty()) {
                throw new NoContentException("produto");
            }
            
            for (Product product : products) {
                productDTOs.add(converterService.productToDto(product));
            }
            return productDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ProductDTO read(Long id) {
        try {
            if (id == null) {
                throw new ResourceNotFoundException("produto", id);
            }
            Optional<Product> product = productRepository.findById(id);
            if (product.isEmpty()) {
                throw new ResourceNotFoundException("produto", id);
            }
            return converterService.productToDto(product.get());
        } catch (Exception e) {
            throw e;
        }
    }
}
