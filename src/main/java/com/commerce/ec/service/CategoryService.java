package com.commerce.ec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.ec.DTO.categoryDTO.CategoryDTO;
import com.commerce.ec.exception.ResourceNotFoundException;
import com.commerce.ec.model.category.Category;
import com.commerce.ec.repos.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // ---- CRUD Operations ---
    // CREATE
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        categoryRepository.save(category);
        return convertToDto(category);
    }

    // --- UPDATE ---
    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        category.setCategoryName(categoryDTO.getCategoryName());
        categoryRepository.save(category);
        return convertToDto(category);
    }
    
    // --- DELETE ---
    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        categoryRepository.delete(category);
        if (categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category cannot be deleted with id: " + categoryId);
        }
    }

    // --- READ (By ID) ---
    public CategoryDTO findCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        return convertToDto(category);
    }

    // READ All
    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Helper method to convert Entity to DTO
    private CategoryDTO convertToDto(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }
}