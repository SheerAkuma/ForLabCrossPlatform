package com.example.bookstore.controller;

import com.example.bookstore.model.*;
import com.example.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore")
public class BookStoreController {
    @Autowired
    private BookStoreService deliveryService;

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderRequest request) {
        return deliveryService.createOrder(
                request.getCustomer(),
                request.getItems(),
                request.getDeliveryAddress()
        );
    }

    @PostMapping("/orders/{orderId}/assign/{courierId}")
    public ResponseEntity<String> assignOrderToCourier(@PathVariable Long orderId, @PathVariable Long courierId) {
        deliveryService.assignOrderToCourier(orderId, courierId);
        return ResponseEntity.ok("Order assigned successfully");
    }

    @GetMapping("/orders/{orderId}/track")
    public ResponseEntity<String> trackOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(deliveryService.trackOrder(orderId));
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestBody StatusUpdateRequest request) {
        deliveryService.updateOrderStatus(orderId, request.getStatus());
        return ResponseEntity.ok("Status updated successfully");
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        deliveryService.getOrder(orderId)
                .ifPresent(order -> deliveryService.updateOrderStatus(orderId, "Cancelled"));
        return ResponseEntity.ok("Order cancelled successfully");
    }

    static class OrderRequest {
        private Customer customer;
        private List<Product> items;
        private String deliveryAddress;

        public Customer getCustomer() { return customer; }
        public void setCustomer(Customer customer) { this.customer = customer; }
        public List<Product> getItems() { return items; }
        public void setItems(List<Product> items) { this.items = items; }
        public String getDeliveryAddress() { return deliveryAddress; }
        public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    }

    static class StatusUpdateRequest {
        private String status;

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}