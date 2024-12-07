package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
