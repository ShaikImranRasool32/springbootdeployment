package com.klef.jfsd.handloom_products_backend.controller;

import com.klef.jfsd.handloom_products_backend.model.OrderTable;
import com.klef.jfsd.handloom_products_backend.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderTableController {

    @Autowired
    private OrderTableService orderTableService;

    // Create a new order
    @PostMapping("/add")
    public ResponseEntity<OrderTable> createOrder(@RequestBody OrderTable orderTable) {
        OrderTable createdOrder = orderTableService.createOrder(orderTable);
        return ResponseEntity.ok(createdOrder);
    }

    // Get all orders for a customer by email
    @GetMapping("/customer/{email}")
    public ResponseEntity<List<OrderTable>> getOrdersByCustomerEmail(@PathVariable String email) {
        List<OrderTable> orders = orderTableService.getOrdersByCustomerEmail(email);
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderTable> getOrderById(@PathVariable Long id) {
        Optional<OrderTable> order = orderTableService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update order status
//    @PutMapping("/{id}/status")
//    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam String orderStatus) {
//        boolean updated = orderTableService.updateOrderStatus(id, orderStatus);
//        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
//    }

    // Update payment status of an order
    @PutMapping("/{id}/payment-status")
    public ResponseEntity<Void> updatePaymentStatus(@PathVariable Long id, @RequestParam String paymentStatus) {
        boolean updated = orderTableService.updatePaymentStatus(id, paymentStatus);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

