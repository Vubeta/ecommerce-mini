package com.devjourneyhub.ecommerce.repository;

import com.devjourneyhub.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }