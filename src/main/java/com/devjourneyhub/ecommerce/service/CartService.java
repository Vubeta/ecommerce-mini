package com.devjourneyhub.ecommerce.service;

import com.devjourneyhub.ecommerce.dto.AddCartRequest;
import com.devjourneyhub.ecommerce.dto.CartItemDto;
import com.devjourneyhub.ecommerce.dto.UpdateCartRequest;

import java.util.List;

public interface CartService {
    List<CartItemDto> getCartForUser(String username);
    CartItemDto addToCart(String username, AddCartRequest request);
    CartItemDto updateCart(String username, UpdateCartRequest request);
    void removeFromCart(String username, Long cartItemId);
}