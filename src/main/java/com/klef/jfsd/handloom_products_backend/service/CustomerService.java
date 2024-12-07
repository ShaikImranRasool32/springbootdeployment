package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Customer;
import com.klef.jfsd.handloom_products_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Register a new customer
    public Customer registerCustomer(Customer customer) {
        // Ensure the role is set to "CUSTOMER" when registering
        customer.setRole("CUSTOMER");
        return customerRepository.save(customer);
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Update customer details
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            return customerRepository.save(customer);
        }
        return null;
    }
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Update customer profile by email
    public Customer updateCustomerByEmail(String email, Customer updatedCustomer) {
        Optional<Customer> customerOpt = getCustomerByEmail(email);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            // Update only non-null fields
            if (updatedCustomer.getName() != null) customer.setName(updatedCustomer.getName());
            if (updatedCustomer.getAddress() != null) customer.setAddress(updatedCustomer.getAddress());
            if (updatedCustomer.getPhoneNumber() != null) customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            return customerRepository.save(customer); // Save the updated customer
        }
        throw new RuntimeException("Customer not found with email: " + email);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // Login: Check if the credentials are correct
    public Optional<Customer> loginCustomer(String email, String password) {
        Optional<Customer> customerOpt = customerRepository.findByEmail(email);
        if (customerOpt.isPresent() && customerOpt.get().getPassword().equals(password)) {
            return customerOpt;
        }
        return Optional.empty(); // Invalid credentials
    }
}
