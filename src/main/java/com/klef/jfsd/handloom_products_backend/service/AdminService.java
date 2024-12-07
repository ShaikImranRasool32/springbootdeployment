package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Admin;
import com.klef.jfsd.handloom_products_backend.model.Customer;
import com.klef.jfsd.handloom_products_backend.model.Seller;
import com.klef.jfsd.handloom_products_backend.repository.AdminRepository;
import com.klef.jfsd.handloom_products_backend.repository.CustomerRepository;
import com.klef.jfsd.handloom_products_backend.repository.SellerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private SellerRepository sellerRepository;

    public Admin registerAdmin(Admin admin) {
        // You can add logic to set a default role here if needed.
        return adminRepository.save(admin);
    }
   

    public Admin loginAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;  // Return the admin object if credentials match
        }
        return null;  // Return null if no matching admin found or password is incorrect
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Optional<Admin> adminOpt = adminRepository.findById(id);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            admin.setEmail(updatedAdmin.getEmail());
            admin.setName(updatedAdmin.getName()); // Add this line if 'name' is part of Admin model

            admin.setPassword(updatedAdmin.getPassword());
            return adminRepository.save(admin);
        }
        return null;
    }
    
    
    
    
    
    
 // Customer CRUD operations
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        return customerOpt.orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer existingCustomer = customerOpt.get();
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setPassword(updatedCustomer.getPassword());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
    
 // Seller CRUD operations
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long sellerId) {
        Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
        return sellerOpt.orElse(null);
    }

    public Seller addSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long sellerId, Seller updatedSeller) {
        Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
        if (sellerOpt.isPresent()) {
            Seller existingSeller = sellerOpt.get();
            existingSeller.setName(updatedSeller.getName());
//            existingSeller.setGmail(updatedSeller.getGmail());
//            existingSeller.setEmail(updatedSeller.getEmail());
            existingSeller.setPassword(updatedSeller.getPassword());
            existingSeller.setPhoneNumber(updatedSeller.getPhoneNumber());
//            
            existingSeller.setAddress(updatedSeller.getAddress());
            existingSeller.setCompanyName(updatedSeller.getCompanyName()); // Example additional field
            return sellerRepository.save(existingSeller);
        }
        return null;
    }

    public void deleteSeller(Long sellerId) {
        sellerRepository.deleteById(sellerId);
    }

}
