package com.gestfood.gestfood.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestfood.gestfood.business.dtos.ProductDTO;
import com.gestfood.gestfood.business.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDto) {
        boolean result = productService.save(productDto);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product.");
        }

        return ResponseEntity.ok().body("Product created successfully!");
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDto) {
        boolean result = productService.update(productDto);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product.");
        }

        return ResponseEntity.ok().body("Product updated successfully!");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO productDto) {
        boolean result = productService.delete(productDto);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product.");
        }

        return ResponseEntity.ok().body("Product deleted successfully!");
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> products = productService.findAll();
        if (products == null || products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No products found.");
        }

        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        if (productDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }
        
        return ResponseEntity.ok(productDTO);
    }

}
