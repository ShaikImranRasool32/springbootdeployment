package com.klef.jfsd.handloom_products_backend.controller;

import com.klef.jfsd.handloom_products_backend.model.CartItem;
import com.klef.jfsd.handloom_products_backend.service.CartItemService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartitem")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Add a product to the cart
    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) {
        System.out.println("Received cart item: " + cartItem);  // Debug log
        CartItem savedItem = cartItemService.addToCart(cartItem);
        return ResponseEntity.ok(savedItem); // Return the saved cart item
    }
    // Get all cart items for a customer by email
    @GetMapping("/all/{customerEmail}")
    public ResponseEntity<List<CartItem>> getAllCartItems(@PathVariable String customerEmail) {
        List<CartItem> cartItems = cartItemService.getAllCartItemsByCustomerEmail(customerEmail);
        return ResponseEntity.ok(cartItems);
    }
    // Update item count in the cart
    @PutMapping("/updateCount/{id}/{count}")
    public ResponseEntity<String> updateItemCount(@PathVariable Long id, @PathVariable Integer count) {
        cartItemService.updateItemCount(id, count);
        return ResponseEntity.ok("Item count updated successfully");
    }

    // Remove an item from the cart
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeItem(@PathVariable Long id) {
        cartItemService.removeItem(id);
        return ResponseEntity.ok("Item removed successfully");
    }
 // CartItemController.java
    @GetMapping("/bySellerGmail/{sellerGmail}")
    public ResponseEntity<List<CartItem>> getCartItemsBySellerGmail(@PathVariable String sellerGmail) {
        List<CartItem> cartItems = cartItemService.getCartItemsBySellerGmail(sellerGmail);
        return ResponseEntity.ok(cartItems);
    }

}
