package com.devjourneyhub.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // One-to-many mapped by product.category
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}