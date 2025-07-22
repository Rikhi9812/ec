package com.commerce.ec.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commerce.ec.model.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Define custom query methods if needed

}
