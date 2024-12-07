package com.klef.jfsd.handloom_products_backend.controller;

import com.klef.jfsd.handloom_products_backend.model.Customer;
import com.klef.jfsd.handloom_products_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signupCustomer(@RequestBody Customer customer) {
        try {
            customerService.registerCustomer(customer);
            return new ResponseEntity<>("Customer registered successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register customer.", HttpStatus.BAD_REQUEST);
        }
    }

    // Login endpoint
//    @PostMapping("/login")
//    public ResponseEntity<String> loginCustomer(@RequestBody Customer customer) {
//        Optional<Customer> customerOpt = customerService.loginCustomer(customer.getEmail(), customer.getPassword());
//        if (customerOpt.isPresent()) {
//            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid credentials. Please try again.", HttpStatus.UNAUTHORIZED);
//        }
//    }
 // Login endpoint
//    @PostMapping("/login")
//    public ResponseEntity<Object> loginCustomer(@RequestBody Customer customer) {
//        Optional<Customer> customerOpt = customerService.loginCustomer(customer.getEmail(), customer.getPassword());
//        if (customerOpt.isPresent()) {
//            Customer loggedInCustomer = customerOpt.get();
//            return new ResponseEntity<>(loggedInCustomer, HttpStatus.OK); // Return customer object for frontend
//        } else {
//            return new ResponseEntity<>("Invalid credentials. Please try again.", HttpStatus.UNAUTHORIZED);
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Customer user) {
        Map<String, Object> response = new HashMap<>();

        // Validate the user's credentials
        Optional<Customer> loggedInCustomer = customerService.loginCustomer(user.getEmail(), user.getPassword());

        if (loggedInCustomer.isPresent()) {
            Customer customer = loggedInCustomer.get();
            response.put("success", true);
            response.put("userName", customer.getName()); // Include the customer's name
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }




    // Get customer by ID (if needed)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        return customerOpt.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update customer details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(id, updatedCustomer);
        if (customer != null) {
            return new ResponseEntity<>("Customer details updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }
    }
    // Get customer profile by ID
    @GetMapping("/profile/{id}")
    public ResponseEntity<Customer> getCustomerProfile(@PathVariable Long id) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        return customerOpt.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update customer profile by ID
    @PutMapping("/profile/{id}")
    public ResponseEntity<String> updateCustomerProfile(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            // Update fields only if they are not null
            if (updatedCustomer.getName() != null) customer.setName(updatedCustomer.getName());
            if (updatedCustomer.getEmail() != null) customer.setEmail(updatedCustomer.getEmail());
            if (updatedCustomer.getAddress() != null) customer.setAddress(updatedCustomer.getAddress());
            if (updatedCustomer.getPhoneNumber() != null) customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            
            customerService.updateCustomer(id, customer);  // Update the customer details in DB
            return new ResponseEntity<>("Profile updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }
    }
 // Get customer profile by email
    @GetMapping("/profile/email/{email}")
    public ResponseEntity<Customer> getCustomerProfileByEmail(@PathVariable String email) {
        Optional<Customer> customerOpt = customerService.getCustomerByEmail(email);
        return customerOpt.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update customer profile by email
    @PutMapping("/profile/email/{email}")
    public ResponseEntity<String> updateCustomerProfileByEmail(@PathVariable String email, @RequestBody Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerService.getCustomerByEmail(email);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            // Update only non-null fields
            if (updatedCustomer.getName() != null) customer.setName(updatedCustomer.getName());
            if (updatedCustomer.getAddress() != null) customer.setAddress(updatedCustomer.getAddress());
            if (updatedCustomer.getPhoneNumber() != null) customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            
            customerService.saveCustomer(customer); // Save the updated customer
            return new ResponseEntity<>("Profile updated successfully.", HttpStatus.OK);
        } else {
        	
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }
    }


}
