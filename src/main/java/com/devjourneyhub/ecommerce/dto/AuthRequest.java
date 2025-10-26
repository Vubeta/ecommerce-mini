package com.devjourneyhub.ecommerce.dto;

import lombok.Data;

/**
 * DTO received from client when logging in.
 */
@Data
public class AuthRequest {
    private String username; // or email depending on your user model
    private String password;
}