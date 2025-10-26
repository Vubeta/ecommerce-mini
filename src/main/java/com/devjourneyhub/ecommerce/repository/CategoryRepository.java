package com.devjourneyhub.ecommerce.repository;

import com.devjourneyhub.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }