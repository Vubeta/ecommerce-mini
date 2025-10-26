package com.devjourneyhub.ecommerce.service.impl;

import com.devjourneyhub.ecommerce.dto.ProductDto;
import com.devjourneyhub.ecommerce.entity.Category;
import com.devjourneyhub.ecommerce.entity.Product;
import com.devjourneyhub.ecommerce.exception.ResourceNotFoundException;
import com.devjourneyhub.ecommerce.repository.CategoryRepository;
import com.devjourneyhub.ecommerce.repository.ProductRepository;
import com.devjourneyhub.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto dto) {
        Category cat = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product p = new Product(dto.getName(), dto.getPrice(), dto.getImageUrl(), cat);
        Product saved = productRepository.save(p);
        return toDto(saved);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        existing.setName(dto.getName() != null ? dto.getName() : existing.getName());
        existing.setDescription(dto.getDescription() != null ? dto.getDescription() : existing.getDescription());
        existing.setPrice(dto.getPrice() != null ? dto.getPrice() : existing.getPrice());
        existing.setImageUrl(dto.getImageUrl() != null ? dto.getImageUrl() : existing.getImageUrl());

        if (dto.getCategoryId() != null) {
            Category cat = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            existing.setCategory(cat);
        }

        Product saved = productRepository.save(existing);
        return toDto(saved);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private ProductDto toDto(Product p) {
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setImageUrl(p.getImageUrl());
        dto.setCategoryId(p.getCategory() != null ? p.getCategory().getId() : null);
        return dto;
    }
}