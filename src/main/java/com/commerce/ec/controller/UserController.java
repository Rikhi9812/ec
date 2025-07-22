package com.commerce.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.ec.DTO.user.CustomerDTO;
import com.commerce.ec.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllListUsers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getUserById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<CustomerDTO> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        CustomerDTO customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(customer);
    }
    @PostMapping
    public ResponseEntity<CustomerDTO> createUser(@Valid @RequestBody CustomerDTO customerDto) {
        CustomerDTO savCustomer = customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateUser(@PathVariable Long id,@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

}
