package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.PaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentItemRepository extends JpaRepository<PaymentItem, Long> {
    // You can add custom query methods if needed
}
