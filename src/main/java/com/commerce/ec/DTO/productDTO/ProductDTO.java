package com.commerce.ec.DTO.productDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data // From Lombok: generates getters, setters, equals, hashCode, toString
public class ProductDTO {
    private Long productId; // Primary Key
    @NotBlank(message = "Product name cannot be blank")
    private String productName;
    private String description; // Optional
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be a positive number")
    private double price;
    @Min(value = 0, message = "Stock quantity must be a positive number")
    private int stockQuantity;
    private String imageUrl; // Optional
    @NotNull(message = "Category cannot be null")
    @Min(value = 1, message = "Category ID must be a positive number")
    private Long categoryId; // Foreign Key to Category

}