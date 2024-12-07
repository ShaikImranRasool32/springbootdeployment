package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.CartItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // You can add custom query methods here if needed
    List<CartItem> findByCustomerEmail(String customerEmail);
 // CartItemRepository.java
    public List<CartItem> findBySellerGmail(String sellerGmail);


}
