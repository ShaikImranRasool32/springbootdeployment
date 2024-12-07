////package com.klef.jfsd.handloom_products_backend.controller;
////
////import com.klef.jfsd.handloom_products_backend.model.Seller;
////import com.klef.jfsd.handloom_products_backend.service.SellerService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/sellers")
////public class SellerController {
////
////    @Autowired
////    private SellerService sellerService;
////
////    @PostMapping("/signup")
////    public Seller registerSeller(@RequestBody Seller seller) {
////        return sellerService.registerSeller(seller);
////    }
////
////    // Login endpoint accepting email and password directly as JSON
////    @PostMapping("/login")
////    public Seller loginSeller(@RequestBody Seller loginCredentials) {
////        return sellerService.loginSeller(loginCredentials.getEmail(), loginCredentials.getPassword());
////    }
////
////    @GetMapping("/{id}")
////    public Seller getSellerById(@PathVariable Long id) {
////        return sellerService.getSellerById(id).orElse(null);
////    }
////
////    @PutMapping("/{id}")
////    public Seller updateSeller(@PathVariable Long id, @RequestBody Seller updatedSeller) {
////        return sellerService.updateSeller(id, updatedSeller);
////    }
////}
//package com.klef.jfsd.handloom_products_backend.controller;
//
//import com.klef.jfsd.handloom_products_backend.model.Seller;
//import com.klef.jfsd.handloom_products_backend.service.SellerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/sellers")
//public class SellerController {
//
//    @Autowired
//    private SellerService sellerService;
//
//    // Add a new seller
//    @PostMapping("/add")
//    public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
//        Seller createdSeller = sellerService.addSeller(seller);
//        return ResponseEntity.ok(createdSeller);
//    }
//
//    // Get a seller by email
//    @GetMapping("/get/{email}")
//    public ResponseEntity<Seller> getSellerByEmail(@PathVariable String email) {
//        Optional<Seller> seller = sellerService.getSellerByEmail(email);
//        if (seller.isPresent()) {
//            return ResponseEntity.ok(seller.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
package com.klef.jfsd.handloom_products_backend.controller;

import com.klef.jfsd.handloom_products_backend.model.Seller;
import com.klef.jfsd.handloom_products_backend.service.SellerService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/sellers")
//public class SellerController {
//
//    @Autowired
//    private SellerService sellerService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<Seller> registerSeller(@RequestBody Seller seller) {
//        Seller createdSeller = sellerService.registerSeller(seller);
//        return ResponseEntity.ok(createdSeller);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Seller> loginSeller(@RequestBody Seller loginCredentials) {
//        Optional<Seller> seller = sellerService.loginSeller(loginCredentials.getSellerEmail(), loginCredentials.getSellerPassword());
//        if (seller.isPresent()) {
//            return ResponseEntity.ok(seller.get());
//        }
//        return ResponseEntity.status(401).body(null);
//    }
//}
//

@RestController
@RequestMapping("/api/seller")
@CrossOrigin(origins = "http://localhost:3000")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    // Register Seller
    @PostMapping("/register")
    public ResponseEntity<String> registerSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerService.registerSeller(seller);
        return ResponseEntity.ok("Seller registered successfully with ID: " + savedSeller.getId());
    }

    // Login Seller
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginSeller(@RequestBody Seller seller) {
        boolean isValid = sellerService.validateSeller(seller.getGmail(), seller.getPassword());

        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            Seller loggedInSeller = sellerService.findByGmail(seller.getGmail());
            response.put("success", true);
            response.put("sellerName", loggedInSeller.getName());
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }

        return ResponseEntity.ok(response);
    }
}
