package com.commerce.ec.model.category;

import java.util.ArrayList;
import java.util.List;

import com.commerce.ec.model.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Data // From Lombok: generates getters, setters, equals, hashCode, toString
@NoArgsConstructor // From Lombok: generates no-arg constructor
@AllArgsConstructor // From Lombok: generates constructor with all fields
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false, unique = true, name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = jakarta.persistence.CascadeType.ALL, fetch = jakarta.persistence.FetchType.LAZY)
    private List<Product> products = new ArrayList<>(); // One-to-Many relationship with Product

}