package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Cart;
import com.klef.jfsd.handloom_products_backend.model.OrderTable;
import com.klef.jfsd.handloom_products_backend.model.Payment;
import com.klef.jfsd.handloom_products_backend.repository.CartRepository;
import com.klef.jfsd.handloom_products_backend.repository.OrderTableRepository;
import com.klef.jfsd.handloom_products_backend.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderTableService {

    @Autowired
    private OrderTableRepository orderTableRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    // Method to create order
    public OrderTable createOrder(OrderTable orderTable) {
        // Fetch cart items for the customer
        List<Cart> cartItems = cartRepository.findByCustomerEmail(orderTable.getCustomerEmail());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place an order.");
        }

        // Calculate the total price from cart items
        double totalPrice = cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getItemPrice() * cartItem.getQuantity())
                .sum();

        // Set values from cart items and other fields
        orderTable.setOrderDate(LocalDateTime.now());  // Set the current date as the order date
        orderTable.setOrderStatus("Pending");  // Default order status
        orderTable.setPaymentStatus("Pending"); // Default payment status
        orderTable.setStatus("Pending");  // Default status
        orderTable.setTotalPrice(totalPrice); // Set the total price

        // Set item details from the cart items
        for (Cart cartItem : cartItems) {
            // Here, you can add items to a list or associate each item properly with the order
            // For example, you may need to create order items if using a relation to store individual items
            // Since you're storing all order information in the `order_table`, set the last item's details (or a summary)
            orderTable.setItemName(cartItem.getItemName());
            orderTable.setFilePath(cartItem.getFilePath());
            orderTable.setItemSummary(cartItem.getItemSummary());
            orderTable.setQuantity(cartItem.getQuantity());
        }

        // Save the order in the database using the repository
        return orderTableRepository.save(orderTable);  // Return the saved orderTable
    }

    // Get all orders by customer email
    public List<OrderTable> getOrdersByCustomerEmail(String customerEmail) {
        return orderTableRepository.findByCustomerEmail(customerEmail);
    }
    // Method to update the order status after successful payment
    public void updateOrderStatusAfterPayment(Long orderId) {
        // Retrieve the order from the repository by ID
        Optional<OrderTable> orderOptional = orderTableRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            OrderTable order = orderOptional.get();

            // Fetch the payment record based on totalPrice matching totalAmount
            Optional<Payment> paymentOptional = paymentRepository.findByEmailAndTotalAmount(order.getCustomerEmail(), order.getTotalPrice());

            if (paymentOptional.isPresent()) {
                Payment payment = paymentOptional.get();

                // Check if the payment status is 'succeeded'
                if ("succeeded".equals(payment.getStatus())) {
                    // Update the order's payment status and order status
                    order.setPaymentStatus("Success");
                    order.setOrderStatus("Completed");

                    // Save the updated order in the database
                    orderTableRepository.save(order);
                }
            } else {
                throw new RuntimeException("Payment record not found for the order.");
            }
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }

    // Update payment status of an order
    public boolean updatePaymentStatus(Long orderId, String paymentStatus) {
        Optional<OrderTable> orderOptional = orderTableRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            OrderTable order = orderOptional.get();
            order.setPaymentStatus(paymentStatus);
            orderTableRepository.save(order);
            return true;
        }
        return false;
    }

    // Get order by ID
    public Optional<OrderTable> getOrderById(Long orderId) {
        return orderTableRepository.findById(orderId);
    }
    
}