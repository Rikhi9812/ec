package com.commerce.ec.model.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data // From Lombok: generates getters, setters, equals, hashCode, toString
@NoArgsConstructor // From Lombok: generates no-arg constructor
@AllArgsConstructor // From Lombok: generates constructor with all fields
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId; // Primary Key
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(nullable = false, name = "password")
    private String password; // In a real application, store hashed passwords, not plain text
    @Column(nullable = false, unique = true, name = "phone_number")
    private String phoneNumber; // Optional
    @Column(nullable = false, name = "registration_date")
    private LocalDateTime registrationDate; // Example: For precise date and time

}