package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.Payment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByEmailAndTotalAmount(String email, Double totalAmount);

}
