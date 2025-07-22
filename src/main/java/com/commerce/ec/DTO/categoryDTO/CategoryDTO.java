package com.commerce.ec.DTO.categoryDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // From Lombok: generates getters, setters, equals, hashCode, toString
public class CategoryDTO {
    private Long categoryId;
    @NotBlank(message = "Category name cannot be blank")
    private String categoryName;

}