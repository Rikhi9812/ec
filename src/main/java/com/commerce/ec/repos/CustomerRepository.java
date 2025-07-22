package com.commerce.ec.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commerce.ec.model.user.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods can be defined here if needed
    // For example, find by email or phone number
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
