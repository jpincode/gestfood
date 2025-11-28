package com.gestfood.gestfood.business.service;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestfood.gestfood.business.dto.product.ProductRequestDTO;
import com.gestfood.gestfood.business.dto.product.ProductResponseDTO;
import com.gestfood.gestfood.business.dto.product.ProductUpdateDTO;
import com.gestfood.gestfood.business.exception.BadRequestException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.business.exception.InternalServerErrorException;
import com.gestfood.gestfood.model.entity.Product;
import com.gestfood.gestfood.model.entity.ProductImage;
import com.gestfood.gestfood.model.enums.ImageType;
import com.gestfood.gestfood.model.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ImageSevice imageSevice;

    @Value("${app.upload.dir}")
    private String DIRECTORY;

    public void create(ProductRequestDTO dto, List<MultipartFile> images) {
        Product product = new Product(dto);

        validateEntity(product);

        try {
            if(images != null && !images.isEmpty()) {
                saveImage(product, images);
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocorreu um erro interno no servidor: não foi possível salvar as imagens.");
        }

        productRepository.save(product);
    }

    @Transactional
    public void update(Long id, ProductUpdateDTO dto, List<MultipartFile> images) {
        validationService.validateId(id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        try {
            if(images != null && !images.isEmpty()) {
                product.getImages().clear();
                saveImage(product, images);
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocorreu um erro interno no servidor: não foi possível salvar as imagens.");
        }

        validateEntity(product);
    }

    @Transactional
    public void delete(Long id) {
        validationService.validateId(id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

        productRepository.delete(product);
    }

    public List<ProductResponseDTO> read() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public ProductResponseDTO read(Long id) {
        validationService.validateId(id);
        return productRepository.findById(id)
                .map(ProductResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    private void saveImage(Product product, List<MultipartFile> images) throws Exception {
        Files.createDirectories(Paths.get(DIRECTORY));

        for (MultipartFile file : images) {
            String originalName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path originalPath = Paths.get(DIRECTORY + originalName);

            Files.copy(file.getInputStream(), originalPath);

            File tempOriginalFile = originalPath.toFile();
            File webpFile = imageSevice.convertToWebp(tempOriginalFile);

            ProductImage img = new ProductImage();
            img.setFileName(webpFile.getName());
            img.setFilePath("/uploads/" + webpFile.getName());
            img.setProduct(product);
            img.setImageType(ImageType.WEBP);
            product.getImages().add(img);

            Files.deleteIfExists(originalPath);
        }
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
