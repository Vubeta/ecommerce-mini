package com.devjourneyhub.ecommerce.service.impl;

import com.devjourneyhub.ecommerce.dto.CategoryDto;
import com.devjourneyhub.ecommerce.entity.Category;
import com.devjourneyhub.ecommerce.exception.ResourceNotFoundException;
import com.devjourneyhub.ecommerce.repository.CategoryRepository;
import com.devjourneyhub.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category c = new Category();
        c.setName(dto.getName());
        Category saved = categoryRepository.save(c);
        return toDto(saved);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        existing.setName(dto.getName() != null ? dto.getName() : existing.getName());
        Category saved = categoryRepository.save(existing);
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) throw new ResourceNotFoundException("Category not found");
        categoryRepository.deleteById(id);
    }

    private CategoryDto toDto(Category c) {
        CategoryDto dto = new CategoryDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        return dto;
    }
}