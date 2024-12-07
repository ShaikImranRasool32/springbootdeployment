////package com.klef.jfsd.handloom_products_backend.controller;
////
////import com.klef.jfsd.handloom_products_backend.service.EmailService;
////import com.klef.jfsd.handloom_products_backend.service.PaymentService;
////import com.klef.jfsd.handloom_products_backend.service.StripeService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.Map;
////
////@RestController
////@RequestMapping("/api/payment")
////public class PaymentController {
////
////    @Autowired
////    private StripeService stripeService;
////
////    @Autowired
////    private PaymentService paymentService;
////    
////    @Autowired
////    private EmailService emailService;
////
////
////    @PostMapping("/create-payment-intent")
////    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> paymentDetails) {
////        int amount = (Integer) paymentDetails.get("amount");
////        String currency = (String) paymentDetails.get("currency");
////
////        try {
////            String clientSecret = stripeService.createPaymentIntent(amount, currency);
////            paymentService.savePayment("generatedIntentId", amount, currency, "pending"); // Update this with actual paymentIntentId from Stripe
////            return Map.of("clientSecret", clientSecret);
////        } catch (Exception e) {
////            e.printStackTrace();
////            return Map.of("error", "Payment initiation failed");
////        }
////    }
////    @PostMapping("/payment-success")
////    public Map<String, String> paymentSuccess(@RequestBody Map<String, Object> paymentDetails) {
////        try {
////            String email = (String) paymentDetails.get("email");
////            String name = (String) paymentDetails.get("name");
////            String orderDetails = (String) paymentDetails.get("orderDetails");
////            Object totalAmountObj = paymentDetails.get("totalAmount");
////            Double totalAmount = null;
////
////            // Handle the case where totalAmount is Integer or Double
////            if (totalAmountObj instanceof Integer) {
////                totalAmount = ((Integer) totalAmountObj).doubleValue(); // Convert Integer to Double
////            } else if (totalAmountObj instanceof Double) {
////                totalAmount = (Double) totalAmountObj; // Directly cast to Double
////            }
////
////            if (totalAmount == null) {
////                throw new Exception("Invalid amount type received.");
////            }
////            // Construct the email content
////            String subject = "Order Confirmation - Thank You for Your Purchase!";
////            String body = "Dear " + name + ",\n\nThank you for your order!\n\n" +
////                          "Order Details: " + orderDetails + "\n" +
////                          "Total Amount: ₹" + totalAmount + "\n\n" +
////                          "We hope you enjoy your purchase. Please let us know if you have any questions.\n\n" +
////                          "Best regards,\nHandloom Products Team";
////
////            // Send email
////            emailService.sendOrderConfirmation(email, subject, body);
////
////
////            // Update payment status in the database
////            paymentService.savePayment("generatedIntentId", (int) (totalAmount * 100), "inr", "succeeded");
////
////            return Map.of("message", "Payment successful, confirmation email sent.");
////        } catch (Exception e) {
////            e.printStackTrace();
////            return Map.of("error", "Error processing payment or sending email.");
////        }
////    }
////}
//package com.klef.jfsd.handloom_products_backend.controller;
//
//import com.klef.jfsd.handloom_products_backend.model.Cart;
//import com.klef.jfsd.handloom_products_backend.model.Order;
//import com.klef.jfsd.handloom_products_backend.model.OrderItem;
//import com.klef.jfsd.handloom_products_backend.service.EmailService;
//import com.klef.jfsd.handloom_products_backend.service.OrderService;
//import com.klef.jfsd.handloom_products_backend.service.PaymentService;
//import com.klef.jfsd.handloom_products_backend.service.StripeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//
//    @Autowired
//    private StripeService stripeService;
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @Autowired
//    private EmailService emailService;
//    @Autowired
//    private OrderService orderService;  // Assuming you have an OrderService to save orders
//
//
//    @PostMapping("/create-payment-intent")
//    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> paymentDetails) {
//        int amount = (Integer) paymentDetails.get("amount");
//        String currency = (String) paymentDetails.get("currency");
//
//        try {
//            String clientSecret = stripeService.createPaymentIntent(amount, currency);
//            return Map.of("clientSecret", clientSecret);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Map.of("error", "Payment initiation failed");
//        }
//    }
//    @PostMapping("/payment-success")
//    public Map<String, String> paymentSuccess(@RequestBody Map<String, Object> paymentDetails) {
//        try {
//            // Extracting details from the request body
//            String email = (String) paymentDetails.get("email");
//            String name = (String) paymentDetails.get("name");
//
//            // Deserialize the cart items properly
//            List<Map<String, Object>> cartItemsData = (List<Map<String, Object>>) paymentDetails.get("cartItems");
//            List<Cart> cartItems = new ArrayList<>();
//
//            // Loop through cartItemsData to populate Cart objects
//            for (Map<String, Object> cartItemData : cartItemsData) {
//                Cart cartItem = new Cart();
//
//                // Safe handling of "id" field
//                Object idObj = cartItemData.get("id");
//                if (idObj instanceof Number) {
//                    cartItem.setId(((Number) idObj).longValue()); // Convert to Long if it's a Number
//                }
//
//                // Set cart item details
//                cartItem.setCustomerEmail((String) cartItemData.get("customer_email"));
//                cartItem.setItemName((String) cartItemData.get("item_name"));
//                cartItem.setItemPrice((Double) cartItemData.get("item_price"));
//                cartItem.setItemSummary((String) cartItemData.get("item_summary"));
//
//                // Safe handling of "quantity" field
//                Object quantityObj = cartItemData.get("quantity");
//                if (quantityObj instanceof Number) {
//                    cartItem.setQuantity(((Number) quantityObj).intValue()); // Convert to Integer if it's a Number
//                }
//
//                cartItem.setFilePath((String) cartItemData.get("file_path"));
//
//                cartItems.add(cartItem);
//            }
//
//            // Handle the totalAmount field
//            Object totalAmountObj = paymentDetails.get("totalAmount");
//            Double totalAmount = null;
//
//            // Handle the case where totalAmount is Integer or Double
//            if (totalAmountObj instanceof Integer) {
//                totalAmount = ((Integer) totalAmountObj).doubleValue(); // Convert Integer to Double
//            } else if (totalAmountObj instanceof Double) {
//                totalAmount = (Double) totalAmountObj; // Directly cast to Double
//            }
//
//            if (totalAmount == null) {
//                throw new Exception("Invalid amount type received.");
//            }
//
//            // Save payment with totalAmount as Double
//            paymentService.savePayment("paymentIntentId", totalAmount, "INR", "succeeded");
//
//            // Send confirmation email
//            emailService.sendOrderDetailsEmail(email, cartItems, totalAmount);
//
//            // Return response
//            return Map.of("message", "Payment successful, confirmation email sent.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Map.of("error", "Error processing payment or sending email.");
//        }
//    }
//}
package com.klef.jfsd.handloom_products_backend.controller;

import com.klef.jfsd.handloom_products_backend.model.Cart;
import com.klef.jfsd.handloom_products_backend.model.Payment;
import com.klef.jfsd.handloom_products_backend.service.EmailService;
import com.klef.jfsd.handloom_products_backend.service.PaymentService;
import com.klef.jfsd.handloom_products_backend.service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailService emailService;

    // Endpoint to create a Payment Intent with Stripe
    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> paymentDetails) {
        int amount = (Integer) paymentDetails.get("amount");
        String currency = (String) paymentDetails.get("currency");

        try {
            String clientSecret = stripeService.createPaymentIntent(amount, currency);
            return Map.of("clientSecret", clientSecret);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Payment initiation failed");
        }
    }

    // Endpoint for handling payment success
//    @PostMapping("/payment-success")
//    public Map<String, String> paymentSuccess(@RequestBody Map<String, Object> paymentDetails) {
//        try {
//            // Extracting basic details from the request body
//            String email = (String) paymentDetails.get("email");
//            String name = (String) paymentDetails.get("name");
//
//            // Deserialize the cart items properly
//            List<Map<String, Object>> cartItemsData = (List<Map<String, Object>>) paymentDetails.get("cartItems");
//            List<Cart> cartItems = new ArrayList<>();
//
//            // Loop through cartItemsData to populate Cart objects
//            for (Map<String, Object> cartItemData : cartItemsData) {
//                Cart cartItem = new Cart();
//
//                // Safe handling of "id" field
//                Object idObj = cartItemData.get("id");
//                if (idObj instanceof Number) {
//                    cartItem.setId(((Number) idObj).longValue()); // Convert to Long if it's a Number
//                }
//
//                // Set cart item details
//                cartItem.setCustomerEmail((String) cartItemData.get("customer_email"));
//                cartItem.setItemName((String) cartItemData.get("item_name"));
//                cartItem.setItemPrice((Double) cartItemData.get("item_price"));
//                cartItem.setItemSummary((String) cartItemData.get("item_summary"));
//
//                // Safe handling of "quantity" field
//                Object quantityObj = cartItemData.get("quantity");
//                if (quantityObj instanceof Number) {
//                    cartItem.setQuantity(((Number) quantityObj).intValue()); // Convert to Integer if it's a Number
//                }
//
//                cartItem.setFilePath((String) cartItemData.get("file_path"));
//
//                cartItems.add(cartItem);
//            }
//
//            // Handle the totalAmount field
//            Object totalAmountObj = paymentDetails.get("totalAmount");
//            Double totalAmount = null;
//
//            // Handle the case where totalAmount is Integer or Double
//            if (totalAmountObj instanceof Integer) {
//                totalAmount = ((Integer) totalAmountObj).doubleValue(); // Convert Integer to Double
//            } else if (totalAmountObj instanceof Double) {
//                totalAmount = (Double) totalAmountObj; // Directly cast to Double
//            }
//
//            if (totalAmount == null) {
//                throw new Exception("Invalid amount type received.");
//            }
//
//            // Save payment with totalAmount as Double
//            paymentService.savePayment("paymentIntentId", totalAmount, "INR", "succeeded");
//
//            // Send order confirmation email
//            emailService.sendOrderDetailsEmail(email, cartItems, totalAmount);
//
//            // Return a success response
//            return Map.of("message", "Payment successful, confirmation email sent.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Map.of("error", "Error processing payment or sending email.");
//        }
//    }
//    @PostMapping("/create")
//    public ResponseEntity<Payment> createPayment(@RequestParam String paymentIntentId, 
//                                                 @RequestParam Double totalAmount, 
//                                                 @RequestParam String currency, 
//                                                 @RequestParam String status, 
//                                                 @RequestParam String email, 
//                                                 @RequestParam String name, 
//                                                 @RequestParam Double totalItemPrice, 
//                                                 @RequestBody List<Cart> cartItems) {
//        // Call the PaymentService to save payment and payment items
//        Payment payment = paymentService.savePayment(paymentIntentId, totalAmount, currency, status, email, name, totalItemPrice, cartItems);
//        return ResponseEntity.ok(payment);  // Return the saved payment
//    }
    @PostMapping("/payment-success")
    public ResponseEntity<Map<String, String>> paymentSuccess(@RequestBody Map<String, Object> paymentDetails) {
        try {
            // Validate necessary fields
            if (paymentDetails.get("email") == null || paymentDetails.get("cartItems") == null || paymentDetails.get("totalAmount") == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing required fields"));
            }

            // Extract payment details
            String email = (String) paymentDetails.get("email");
            String name = (String) paymentDetails.get("name");
            List<Map<String, Object>> cartItemsData = (List<Map<String, Object>>) paymentDetails.get("cartItems");

            List<Cart> cartItems = new ArrayList<>();
            double totalItemPrice = 0.0;

            for (Map<String, Object> cartItemData : cartItemsData) {
                String itemName = (String) cartItemData.get("item_name");
                Double itemPrice = (Double) cartItemData.get("item_price");
                Integer quantity = (Integer) cartItemData.get("quantity");
                String filePath = (String) cartItemData.get("file_path");
                String itemSummary = (String) cartItemData.get("item_summary");

                if (itemPrice != null && quantity != null) {
                    totalItemPrice += itemPrice * quantity;
                }

                Cart cartItem = new Cart();
                cartItem.setCustomerEmail(email);
                cartItem.setItemName(itemName);
                cartItem.setItemPrice(itemPrice);
                cartItem.setQuantity(quantity);
                cartItem.setFilePath(filePath);
                cartItem.setItemSummary(itemSummary);

                cartItems.add(cartItem);
            }

            // Extract the totalAmount and convert it if necessary
            Object totalAmountObj = paymentDetails.get("totalAmount");
            Double totalAmount = null;

            if (totalAmountObj instanceof Integer) {
                totalAmount = ((Integer) totalAmountObj).doubleValue();
            } else if (totalAmountObj instanceof Double) {
                totalAmount = (Double) totalAmountObj;
            }

            if (totalAmount == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid amount type received"));
            }

            // Save payment info to the database
            Payment payment = paymentService.savePayment(
                    "paymentIntentId",  // Placeholder or actual Stripe ID
                    totalAmount,
                    "INR",              // Currency
                    "succeeded",        // Payment status
                    email,
                    name,
                    totalItemPrice,
                    cartItems
            );

            // Send email confirmation
            emailService.sendOrderDetailsEmail(email, cartItems, totalAmount);

            // Return success response
            return ResponseEntity.ok(Map.of("message", "Payment successful, confirmation email sent"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error processing payment or sending email"));
        }
    }
}