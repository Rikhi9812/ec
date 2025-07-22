package com.commerce.ec.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commerce.ec.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query method example
    List<Product> findByProductNameContainingIgnoreCase(String name);

    List<Product> findByCategoryCategoryId(Long categoryId);
}
