package com.klef.jfsd.handloom_products_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.jfsd.handloom_products_backend.model.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomerEmail(String customerEmail);  // Query by customer email
}
