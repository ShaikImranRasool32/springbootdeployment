package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email); // To find customer by email during login

}
