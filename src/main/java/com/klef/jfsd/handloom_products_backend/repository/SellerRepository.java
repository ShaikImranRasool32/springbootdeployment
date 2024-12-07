//package com.klef.jfsd.handloom_products_backend.repository;
//
//import com.klef.jfsd.handloom_products_backend.model.Seller;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface SellerRepository extends JpaRepository<Seller, Long> {
////    Seller findByEmail(String email); // Method to find a seller by email
//    Optional<Seller> findBySellerEmail(String email);
//
//}

package com.klef.jfsd.handloom_products_backend.repository;

import com.klef.jfsd.handloom_products_backend.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByGmail(String gmail); // Find seller by Gmail
}