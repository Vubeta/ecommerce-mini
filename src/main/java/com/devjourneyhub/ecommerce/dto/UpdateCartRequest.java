package com.devjourneyhub.ecommerce.dto;

import lombok.Data;

@Data
public class UpdateCartRequest {
    private Long cartItemId;
    private Integer quantity;
}