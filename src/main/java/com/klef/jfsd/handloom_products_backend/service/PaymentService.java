//package com.klef.jfsd.handloom_products_backend.service;
//
//import com.klef.jfsd.handloom_products_backend.model.Payment;
//import com.klef.jfsd.handloom_products_backend.repository.PaymentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    public Payment savePayment(String paymentIntentId, int amount, String currency, String status) {
//        Payment payment = new Payment();
//        payment.setPaymentIntentId(paymentIntentId);
//        payment.setAmount(amount);
//        payment.setCurrency(currency);
//        payment.setStatus(status);
//        payment.setCreatedAt(LocalDateTime.now());
//        return paymentRepository.save(payment);
//    }
//}
//package com.klef.jfsd.handloom_products_backend.service;
//
//import com.klef.jfsd.handloom_products_backend.model.Payment;
//import com.klef.jfsd.handloom_products_backend.repository.PaymentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    public void savePayment(String paymentIntentId, Double totalAmount, String currency, String status) {
//        Payment payment = new Payment();
//        payment.setPaymentIntentId(paymentIntentId);
//        payment.setAmount(totalAmount);
//        payment.setCurrency(currency);
//        payment.setStatus(status);
//        payment.setCreatedAt(java.time.LocalDateTime.now());
//        
//        paymentRepository.save(payment);
//    }
//}
package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Cart;
import com.klef.jfsd.handloom_products_backend.model.OrderTable;
import com.klef.jfsd.handloom_products_backend.model.Payment;
import com.klef.jfsd.handloom_products_backend.model.PaymentItem;
import com.klef.jfsd.handloom_products_backend.repository.OrderTableRepository;
import com.klef.jfsd.handloom_products_backend.repository.PaymentItemRepository;
import com.klef.jfsd.handloom_products_backend.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;  // Assume you have a repository for Payment

    // Method to save payment information
//    public Payment savePayment(String paymentIntentId, Double totalAmount, String currency, String status) {
//        Payment payment = new Payment();
//        payment.setPaymentIntentId(paymentIntentId);
//        payment.setTotalAmount(totalAmount);
//        payment.setCurrency(currency);
//        payment.setStatus(status);
//        return paymentRepository.save(payment);  // Save payment to the database
//    }
 // Method to save payment information
    @Autowired
    private PaymentItemRepository paymentItemRepository;
    @Autowired
    private OrderTableRepository orderTableRepository; // Repository to interact with Or

    public Payment savePayment(String paymentIntentId, Double totalAmount, String currency, String status,
                               String email, String name, Double totalItemPrice, List<Cart> cartItems) {
        Payment payment = new Payment();
        payment.setPaymentIntentId(paymentIntentId);
        payment.setTotalAmount(totalAmount);
        payment.setCurrency(currency);
        payment.setStatus(status);
        payment.setEmail(email);
        payment.setName(name);
        payment.setTotalItemPrice(totalItemPrice);  // Add the total item price

        // Save the payment object first
        payment = paymentRepository.save(payment);

        // Now, create PaymentItem entries for each cart item and save them
        for (Cart cartItem : cartItems) {
            PaymentItem paymentItem = new PaymentItem();
            paymentItem.setPayment(payment);  // Set the reference to the payment object
            paymentItem.setItemName(cartItem.getItemName());
            paymentItem.setFilePath(cartItem.getFilePath());
            paymentItem.setItemPrice(cartItem.getItemPrice());
            paymentItem.setItemSummary(cartItem.getItemSummary());
            paymentItem.setQuantity(cartItem.getQuantity());

            // Save the PaymentItem to the database
            paymentItemRepository.save(paymentItem);
        }

        return payment;
    }
 // Method to update order status after successful payment
    private void updateOrderStatusAfterPayment(Long orderId, String paymentStatus, String orderStatus) {
        // Retrieve the order by its ID
        OrderTable order = orderTableRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        // Update the payment status and order status
        order.setPaymentStatus(paymentStatus); // Set the payment status to 'Paid'
        order.setOrderStatus(orderStatus); // Set the order status to 'Completed'

        // Save the updated order
        orderTableRepository.save(order);
    }

}