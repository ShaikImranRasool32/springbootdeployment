package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.CartItem;
import com.klef.jfsd.handloom_products_backend.repository.CartItemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CartItemService {

	@Autowired
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addToCart(CartItem cartItem) {
        // Save the cart item to the database
        return cartItemRepository.save(cartItem);
    }
    // Get all cart items for a customer by email
    public List<CartItem> getAllCartItemsByCustomerEmail(String customerEmail) {
        // Make sure that the 'CartItem' entity has the 'customerEmail' field
        return cartItemRepository.findByCustomerEmail(customerEmail);
    }
 // Update item count
    public void updateItemCount(Long id, Integer count) {
        CartItem cartItem = cartItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item with ID " + id + " not found"));
        cartItem.setCount(count);
        cartItemRepository.save(cartItem);  // Save the updated cart item
    }

    // Remove an item from the cart
    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }
 // CartItemService.java
    public List<CartItem> getCartItemsBySellerGmail(String sellerGmail) {
        return cartItemRepository.findBySellerGmail(sellerGmail);  // Adjusting method to use sellerGmail
    }

    
}
