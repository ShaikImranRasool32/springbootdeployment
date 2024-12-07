//package com.klef.jfsd.handloom_products_backend.service;
//
//import com.klef.jfsd.handloom_products_backend.model.Seller;
//import com.klef.jfsd.handloom_products_backend.repository.SellerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class SellerService {
//
//    @Autowired
//    private SellerRepository sellerRepository;
//
//    public Seller registerSeller(Seller seller) {
//        return sellerRepository.save(seller);
//    }
//
//    public Seller loginSeller(String email, String password) {
//        Seller seller = sellerRepository.findByEmail(email);
//        if (seller != null && seller.getPassword().equals(password)) {
//            return seller;  // Return the seller object if credentials match
//        }
//        return null;  // Return null if no matching seller found or password is incorrect
//    }
//
//    public Optional<Seller> getSellerById(Long id) {
//        return sellerRepository.findById(id);
//    }
//
//    public Seller updateSeller(Long id, Seller updatedSeller) {
//        Optional<Seller> sellerOpt = sellerRepository.findById(id);
//        if (sellerOpt.isPresent()) {
//            Seller seller = sellerOpt.get();
//            seller.setEmail(updatedSeller.getEmail());
//            seller.setPassword(updatedSeller.getPassword());
//            return sellerRepository.save(seller);
//        }
//        return null;
//    }
//}
package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Seller;
import com.klef.jfsd.handloom_products_backend.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//
//@Service
//public class SellerService {
//
//    @Autowired
//    private SellerRepository sellerRepository;
//
//    public Seller registerSeller(Seller seller) {
//        return sellerRepository.save(seller);
//    }
//
//    public Optional<Seller> loginSeller(String email, String password) {
//        Optional<Seller> seller = sellerRepository.findBySellerEmail(email);
//        if (seller.isPresent() && seller.get().getSellerPassword().equals(password)) {
//            return seller;
//        }
//        return Optional.empty();
//    }
//}

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller registerSeller(Seller seller) {
        return sellerRepository.save(seller); // Save the seller to the database
    }

    public Seller findByGmail(String gmail) {
        return sellerRepository.findByGmail(gmail);
    }

    public boolean validateSeller(String gmail, String password) {
        Seller seller = sellerRepository.findByGmail(gmail);
        return seller != null && seller.getPassword().equals(password); // Validate Gmail and password
    }
}
