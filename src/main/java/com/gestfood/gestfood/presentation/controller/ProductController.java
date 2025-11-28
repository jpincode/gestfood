package com.gestfood.gestfood.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestfood.gestfood.business.dto.product.ProductRequestDTO;
import com.gestfood.gestfood.business.dto.product.ProductResponseDTO;
import com.gestfood.gestfood.business.dto.product.ProductUpdateDTO;
import com.gestfood.gestfood.business.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO productDto) {
        productService.create(productDto);
        return ResponseEntity.ok().body("Produto cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO productDto) {
        productService.update(id, productDto);
        return ResponseEntity.ok().body("Produto atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().body("Produto deletado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok().body(productService.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.read(id));
    }

}
