package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderTableRepository extends JpaRepository<OrderTable, Long> {
    List<OrderTable> findByCustomerEmail(String customerEmail);
    
}
