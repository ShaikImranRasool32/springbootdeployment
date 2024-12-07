//package com.klef.jfsd.handloom_products_backend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.klef.jfsd.handloom_products_backend.model.Cart;
//import com.klef.jfsd.handloom_products_backend.service.CartService;
//
//import java.util.List;
//@RestController
//@RequestMapping("/api/cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addItemToCart(@RequestBody Cart cart) {
//        Cart savedCartItem = cartService.addItemToCart(cart);
//        return ResponseEntity.ok("Item added to cart: " + savedCartItem.getItemName());
//    }
//
//    @GetMapping("/customer/{customerEmail}")
//    public ResponseEntity<List<Cart>> getCartItemsByCustomerEmail(@PathVariable String customerEmail) {
//        List<Cart> cartItems = cartService.getCartItemsByCustomerEmail(customerEmail);
//        return ResponseEntity.ok(cartItems);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateCartItem(@PathVariable Long id, @RequestBody Cart updatedCartItem) {
//        if (cartService.updateCartItem(id, updatedCartItem)) {
//            return ResponseEntity.ok("Cart item updated successfully");
//        } else {
//            return ResponseEntity.status(404).body("Cart item not found");
//        }
//    }
//
//    @DeleteMapping("/remove/{id}")
//    public ResponseEntity<String> removeCartItem(@PathVariable Long id) {
//        cartService.removeCartItem(id);
//        return ResponseEntity.ok("Cart item removed successfully");
//    }
//}

package com.klef.jfsd.handloom_products_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.klef.jfsd.handloom_products_backend.model.Cart;
import com.klef.jfsd.handloom_products_backend.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add item to the cart (check if it's already added, update quantity if needed)
    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestBody Cart cart) {
        // Check if the item already exists in the cart
        if (cartService.addItemToCart(cart)) {
            return ResponseEntity.ok("Item added to cart: " + cart.getItemName());
        } else {
            return ResponseEntity.status(400).body("Item already exists in cart, updated quantity.");
        }
    }

    // Get cart items by customer's email
    @GetMapping("/customer/{customerEmail}")
    public ResponseEntity<List<Cart>> getCartItemsByCustomerEmail(@PathVariable String customerEmail) {
        List<Cart> cartItems = cartService.getCartItemsByCustomerEmail(customerEmail);
        if (cartItems.isEmpty()) {
            return ResponseEntity.status(404).body(null); // Return 404 if no items are found
        }
        return ResponseEntity.ok(cartItems);
    }
//
//    @GetMapping("/customer/{customerEmail}")
//    public ResponseEntity<List<Cart>> getCartItemsByCustomerEmail(@PathVariable String customerEmail) {
//        List<Cart> cartItems = cartService.getCartItemsByCustomerEmail(customerEmail);
//        cartItems.forEach(item -> {
//            System.out.println("Item Name: " + item.getItemName());
//            System.out.println("Item Price: " + item.getItemPrice());
//            System.out.println("Quantity: " + item.getQuantity());
//        });
//        return ResponseEntity.ok(cartItems);
//    }

    // Update cart item by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long id, @RequestBody Cart updatedCartItem) {
        boolean isUpdated = cartService.updateCartItem(id, updatedCartItem);
        if (isUpdated) {
            return ResponseEntity.ok("Cart item updated successfully");
        } else {
            return ResponseEntity.status(404).body("Cart item not found");
        }
    }
    // Clear all cart items for a customer (e.g., after successful checkout)
    @DeleteMapping("/clear/{customerEmail}")
    public ResponseEntity<String> clearCart(@PathVariable String customerEmail) {
        boolean isCleared = cartService.clearCart(customerEmail);
        if (isCleared) {
            return ResponseEntity.ok("Cart cleared successfully.");
        } else {
            return ResponseEntity.status(404).body("No cart items found to clear.");
        }
    }
    // Example: Calculate total price of cart for a customer
    @GetMapping("/customer/{customerEmail}/total")
    public ResponseEntity<Double> calculateTotalPrice(@PathVariable String customerEmail) {
        double totalPrice = cartService.calculateTotalPrice(customerEmail);
        return ResponseEntity.ok(totalPrice);
    }

    // Check if a cart exists for a customer (before adding items or starting checkout)
    @GetMapping("/exists/{customerEmail}")
    public ResponseEntity<Boolean> checkIfCartExists(@PathVariable String customerEmail) {
        boolean cartExists = cartService.cartExists(customerEmail);
        return ResponseEntity.ok(cartExists);
    }

    // Remove cart item by ID
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long id) {
        boolean isRemoved = cartService.removeCartItem(id);
        if (isRemoved) {
            return ResponseEntity.ok("Cart item removed successfully");
        } else {
            return ResponseEntity.status(404).body("Cart item not found");
        }
    }
    @GetMapping("/{customerEmail}")
    public List<Cart> getCartItems(@PathVariable String customerEmail) {
        return cartService.getCartItemsByCustomer(customerEmail);
    }
}
