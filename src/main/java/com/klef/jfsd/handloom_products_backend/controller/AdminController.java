package com.klef.jfsd.handloom_products_backend.controller;

import com.klef.jfsd.handloom_products_backend.model.Admin;
import com.klef.jfsd.handloom_products_backend.model.Customer;
import com.klef.jfsd.handloom_products_backend.model.Seller;
import com.klef.jfsd.handloom_products_backend.service.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    

    @PostMapping("/signup")
    public Admin registerAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }

//    // Modify this endpoint to accept JSON body
//    @PostMapping("/login")
//    public Admin loginAdmin(@RequestBody Admin loginDetails) {
//        return adminService.loginAdmin(loginDetails.getEmail(), loginDetails.getPassword());
//    }
    // Admin login
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin loginDetails) {
        Admin admin = adminService.loginAdmin(loginDetails.getEmail(), loginDetails.getPassword());
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK); // Return admin object on success
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED); // Return error message
        }
    }


    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        return adminService.updateAdmin(id, updatedAdmin);
    }
    
    
    
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {
        return adminService.getCustomerById(customerId);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return adminService.addCustomer(customer);
    }

    @PutMapping("/customers/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        return adminService.updateCustomer(customerId, updatedCustomer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        adminService.deleteCustomer(customerId);
    }
    
    
    
    // Seller CRUD operations
    @GetMapping("/sellers")
    public List<Seller> getAllSellers() {
        return adminService.getAllSellers();
    }

    @GetMapping("/sellers/{sellerId}")
    public Seller getSellerById(@PathVariable Long sellerId) {
        return adminService.getSellerById(sellerId);
    }

    @PostMapping("/sellers")
    public Seller addSeller(@RequestBody Seller seller) {
        return adminService.addSeller(seller);
    }

    @PutMapping("/sellers/{sellerId}")
    public Seller updateSeller(@PathVariable Long sellerId, @RequestBody Seller updatedSeller) {
        return adminService.updateSeller(sellerId, updatedSeller);
    }

    @DeleteMapping("/sellers/{sellerId}")
    public void deleteSeller(@PathVariable Long sellerId) {
        adminService.deleteSeller(sellerId);
    }
    
}
