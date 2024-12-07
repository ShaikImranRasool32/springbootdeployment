//package com.klef.jfsd.handloom_products_backend.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.klef.jfsd.handloom_products_backend.model.Cart;
//import com.klef.jfsd.handloom_products_backend.repository.CartRepository;
//
//import java.util.List;
//import java.util.Optional;
//@Service
//public class CartService {
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    public Cart addItemToCart(Cart cart) {
//        return cartRepository.save(cart);  // Save cart item
//    }
//
//    public List<Cart> getCartItemsByCustomerEmail(String customerEmail) {
//        return cartRepository.findByCustomerEmail(customerEmail);  // Fetch cart items by customer email
//    }
//
//    public boolean updateCartItem(Long id, Cart updatedCartItem) {
//        Optional<Cart> existingCartItem = cartRepository.findById(id);
//        if (existingCartItem.isPresent()) {
//            Cart cartItem = existingCartItem.get();
//            cartItem.setItemName(updatedCartItem.getItemName());
//            cartItem.setItemPrice(updatedCartItem.getItemPrice());
//            cartItem.setItemSummary(updatedCartItem.getItemSummary());
//            cartItem.setQuantity(updatedCartItem.getQuantity());
//            cartItem.setFilePath(updatedCartItem.getFilePath());  // Update file path
//            cartRepository.save(cartItem);
//            return true;
//        }
//        return false;
//    }
//
//    public void removeCartItem(Long id) {
//        cartRepository.deleteById(id);  // Delete cart item by ID
//    }
//}
package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Cart;
import com.klef.jfsd.handloom_products_backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Add item to cart (check if already exists, update quantity if needed)
    public boolean addItemToCart(Cart cart) {
        List<Cart> existingCartItems = cartRepository.findByCustomerEmail(cart.getCustomerEmail());

        // Check if the product already exists in the cart for this customer
        for (Cart existingCartItem : existingCartItems) {
            if (existingCartItem.getItemName().equals(cart.getItemName())) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + cart.getQuantity()); // Update quantity
                cartRepository.save(existingCartItem); // Save updated item
                return false; // Item already exists, updated quantity
            }
        }

        // If the item doesn't exist in the cart, add it
        cartRepository.save(cart);
        return true; // Item added successfully
    }

    // Get cart items by customer email
    public List<Cart> getCartItemsByCustomerEmail(String email) {
        return cartRepository.findByCustomerEmail(email);
    }

    // Update cart item by ID
//    public boolean updateCartItem(Long id, Cart updatedCartItem) {
//        Optional<Cart> existingCartItemOpt = cartRepository.findById(id);
//
//        if (existingCartItemOpt.isPresent()) {
//            Cart existingCartItem = existingCartItemOpt.get();
//            existingCartItem.setQuantity(updatedCartItem.getQuantity()); // Update the quantity
//            existingCartItem.setItemPrice(updatedCartItem.getItemPrice()); // Update the price (if necessary)
//            cartRepository.save(existingCartItem); // Save the updated item
//            return true;
//        } else {
//            return false; // Item not found
//        }
//    }
    public boolean updateCartItem(Long id, Cart updatedCartItem) {
        Optional<Cart> existingCartItemOpt = cartRepository.findById(id);

        if (existingCartItemOpt.isPresent()) {
            Cart existingCartItem = existingCartItemOpt.get();

            // Update only the quantity and leave the price unchanged
            if (updatedCartItem.getQuantity() != null) {
                existingCartItem.setQuantity(updatedCartItem.getQuantity());
            }

            // Ensure itemPrice remains unchanged if not explicitly updated
            if (updatedCartItem.getItemPrice() != null) {
                existingCartItem.setItemPrice(updatedCartItem.getItemPrice());
            }

            cartRepository.save(existingCartItem); // Save the updated item
            return true;
        }

        return false; // Item not found
    }

    // Remove cart item by ID
    public boolean removeCartItem(Long id) {
        Optional<Cart> cartItemOpt = cartRepository.findById(id);

        if (cartItemOpt.isPresent()) {
            cartRepository.deleteById(id); // Remove the item
            return true;
        } else {
            return false; // Item not found
        }
    }
    public List<Cart> getCartItemsByCustomer(String customerEmail) {
        return cartRepository.findByCustomerEmail(customerEmail);
    }

    public void clearCart(List<Cart> cartItems) {
        cartRepository.deleteAll(cartItems);
    }
//    public double calculateTotalPrice(String customerEmail) {
//        List<Cart> cartItems = cartRepository.findByCustomerEmail(customerEmail);
//        return cartItems.stream()
//                        .mapToDouble(item -> item.getItemPrice() * item.getQuantity())
//                        .sum();
//    }
    public boolean cartExists(String customerEmail) {
        List<Cart> cartItems = cartRepository.findByCustomerEmail(customerEmail);
        return !cartItems.isEmpty();  // Return true if cart exists, false otherwise
    }


    public boolean clearCart(String customerEmail) {
        List<Cart> cartItems = cartRepository.findByCustomerEmail(customerEmail);
        if (cartItems.isEmpty()) {
            return false;  // No items to clear
        }
        cartRepository.deleteAll(cartItems);  // Delete all items in the cart
        return true;
    }

    public double calculateTotalPrice(String customerEmail) {
        List<Cart> cartItems = cartRepository.findByCustomerEmail(customerEmail);
        double totalPrice = 0;
        for (Cart item : cartItems) {
            totalPrice += item.getItemPrice() * item.getQuantity();
        }
        return totalPrice;
    }

}
