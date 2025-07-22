package com.commerce.ec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.ec.DTO.productDTO.ProductDTO;
import com.commerce.ec.exception.ResourceNotFoundException;
import com.commerce.ec.model.category.Category;
import com.commerce.ec.model.product.Product;
import com.commerce.ec.repos.CategoryRepository;
import com.commerce.ec.repos.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // --- CREATE ---
    @Transactional
    public ProductDTO createProduct(ProductDTO ProductDTO) {
        // Find the category by ID
        Category category = categoryRepository.findById(ProductDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + ProductDTO.getCategoryId()));

        Product product = new Product();
        product.setProductName(ProductDTO.getProductName());
        product.setDescription(ProductDTO.getDescription());
        product.setPrice(ProductDTO.getPrice());
        product.setStockQuantity(ProductDTO.getStockQuantity());
        product.setImageUrl(ProductDTO.getImageUrl());
        product.setCategory(category); // Set the Category entity

        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    // --- READ (All) ---
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // --- READ (By ID) ---
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return convertToDto(product);
    }

    // --- UPDATE ---
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO ProductDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // Find the category by ID
        Category category = categoryRepository.findById(ProductDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + ProductDTO.getCategoryId()));

        existingProduct.setProductName(ProductDTO.getProductName());
        existingProduct.setDescription(ProductDTO.getDescription());
        existingProduct.setPrice(ProductDTO.getPrice());
        existingProduct.setStockQuantity(ProductDTO.getStockQuantity());
        existingProduct.setImageUrl(ProductDTO.getImageUrl());
        existingProduct.setCategory(category); // Update the Category

        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDto(updatedProduct);
    }

    // --- DELETE ---
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        if (productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product cannot be deleted with id: " + id);
        }
    }

    // Helper method to convert Entity to DTO
    private ProductDTO convertToDto(Product product) {
        // Find the category by ID
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setImageUrl(product.getImageUrl());
        dto.setCategoryId(product.getCategory().getCategoryId()); // Get Category from entity
        return dto;
    }

    // Helper method to convert DTO to Entity (useful for complex DTOs, less
    // critical here as we update existing)
    // private Product convertToEntity(ProductDTO ProductDTO) { ... }
}