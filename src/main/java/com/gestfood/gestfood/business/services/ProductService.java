package com.gestfood.gestfood.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dtos.ProductDTO;
import com.gestfood.gestfood.models.entities.Product;
import com.gestfood.gestfood.models.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ConverterService converterService;

    public void save(ProductDTO productDTO) {
        try {
            Product product = converterService.dtoToProduct(productDTO);
            if (product == null) {
                throw new RuntimeException("Converted product is null");
            }
            productRepository.save(product);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void update(ProductDTO productDTO) {
        try {
            Product product = converterService.dtoToProduct(productDTO);
            if (product == null) {
                throw new RuntimeException("Converted product is null");
            }
            productRepository.save(product);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void delete(ProductDTO productDTO) {
        try {
            Product product = converterService.dtoToProduct(productDTO);
            if (product == null) {
                throw new RuntimeException("Converted product is null");
            }
            productRepository.delete(product);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ProductDTO> findAll() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOs = new ArrayList<>();

            if(products.isEmpty()) {
                throw new RuntimeException("There are no products listed.");
            }
            
            for (Product product : products) {
                productDTOs.add(converterService.productToDto(product));
            }
            return productDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    public ProductDTO findById(Long id) {
        try {
            if (id == null) {
                throw new RuntimeException("Product not found with id: " + id);
            }
            Optional<Product> product = productRepository.findById(id);
            if (product.isEmpty()) {
                throw new RuntimeException("Product not found with id: " + id);
            }
            return converterService.productToDto(product.get());
        } catch (Exception e) {
            throw e;
        }
    }
}
