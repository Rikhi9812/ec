package com.commerce.ec.DTO.user;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // From Lombok: generates getters, setters, equals, hashCode, toString
public class CustomerDTO {
    private Long customerId; // Primary Key
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password; // In a real application, store hashed passwords, not plain text
    @NotBlank(message = "Phone number is required")
    private String phoneNumber; // Optional
    private LocalDateTime registrationDate; // Example: For precise date and time

}