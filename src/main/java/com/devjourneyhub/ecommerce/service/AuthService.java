package com.devjourneyhub.ecommerce.service;

import com.devjourneyhub.ecommerce.dto.AuthRequest;
import com.devjourneyhub.ecommerce.dto.AuthResponse;
import com.devjourneyhub.ecommerce.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}