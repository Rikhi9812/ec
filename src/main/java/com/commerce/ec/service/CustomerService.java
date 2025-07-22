package com.commerce.ec.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.ec.DTO.user.CustomerDTO;
import com.commerce.ec.exception.ResourceNotFoundException;
import com.commerce.ec.model.user.Customer;
import com.commerce.ec.repos.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setRegistrationDate(LocalDateTime.now());
        Customer savCustomer =  customerRepository.save(customer);

        return convertToDto(savCustomer);

    }
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return convertToDto(customer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        Customer updatedCustomer = customerRepository.save(customer);
        return convertToDto(updatedCustomer);
    }
    
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
        if (customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer cannot be deleted with id: " + id);
        }
    }

    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> 
            new ResourceNotFoundException("Customer not found with email: " + email));
        return convertToDto(customer);
    }

    public CustomerDTO getCustomerByPhoneNumber(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> 
            new ResourceNotFoundException("Customer not found with phone number: " + phoneNumber));
        return convertToDto(customer);
    }

    private CustomerDTO convertToDto(Customer savCustomer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(savCustomer.getCustomerId());
        dto.setFirstName(savCustomer.getFirstName());
        dto.setLastName(savCustomer.getLastName());
        dto.setEmail(savCustomer.getEmail());
        dto.setPassword(savCustomer.getPassword());
        dto.setPhoneNumber(savCustomer.getPhoneNumber());
        dto.setRegistrationDate(savCustomer.getRegistrationDate());
        return dto;
    }

}
