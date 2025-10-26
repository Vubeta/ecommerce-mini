package com.devjourneyhub.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long categoryId;

    // Factory helpers can be added in service to map between entity and dto
}