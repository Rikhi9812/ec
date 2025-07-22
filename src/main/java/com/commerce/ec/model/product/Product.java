package com.commerce.ec.model.product;

import com.commerce.ec.model.category.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data // From Lombok: generates getters, setters, equals, hashCode, toString
@NoArgsConstructor // From Lombok: generates no-arg constructor
@AllArgsConstructor // From Lombok: generates constructor with all fields
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId; // Primary Key
    @Column(nullable = false, unique = true, name="product_name")
    private String productName;
    @Column(nullable = true, name="product_description", columnDefinition = "TEXT")
    private String description; // Optional
    @Column(nullable = false, name="product_price", columnDefinition = "DECIMAL(10,2)")
    private double price;
    @Column(nullable = false, name="product_stock_quantity", columnDefinition = "INT DEFAULT 0")
    private int stockQuantity;
    @Column(nullable = true, name="product_image_url")
    private String imageUrl; // Optional
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Foreign Key to Category

}