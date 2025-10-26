package com.devjourneyhub.ecommerce.dto;

import lombok.Data;

@Data
public class AddCartRequest {
    private Long productId;
    private Integer quantity;
}