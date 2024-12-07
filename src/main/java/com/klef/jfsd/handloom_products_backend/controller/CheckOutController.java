package com.klef.jfsd.handloom_products_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.handloom_products_backend.model.CartItem;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

    @PostMapping("/calculate-total")
    public ResponseEntity<Double> calculateTotal(@RequestBody List<CartItem> cartItems) {
        // Calculate total price by multiplying item price with the quantity (count)
        double total = cartItems.stream()
                .mapToDouble(item -> item.getItemPrice() * item.getCount())
                .sum();
        return ResponseEntity.ok(total);
    }
}
