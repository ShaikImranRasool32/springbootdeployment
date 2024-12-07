package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.MarketingExpert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketingExpertRepository extends JpaRepository<MarketingExpert, Long> {
    MarketingExpert findByEmail(String email);
}
