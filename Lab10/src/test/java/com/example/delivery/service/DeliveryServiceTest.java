package com.example.bookstore.service;

import com.example.bookstore.model.*;
import com.example.bookstore.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryServiceTest {

    @Autowired
    private BookStoreService deliveryService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CourierRepository courierRepository;

    @Test
    void testCreateOrder() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("1234567890");

        Product product = new Product();
        product.setName("Book");
        product.setQuantity(2);
        product.setPrice(10.0);

        Order order = deliveryService.createOrder(customer, List.of(product), "123 Main St");

        assertNotNull(order);
        assertEquals("Processing", order.getStatus());
        assertEquals("123 Main St", order.getDeliveryAddress());
    }

    @Test
    void testAssignOrderToCourier() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer = customerRepository.save(customer);

        Product product = new Product();
        product.setName("Book");
        product.setQuantity(2);
        product.setPrice(10.0);

        Order order = deliveryService.createOrder(customer, List.of(product), "123 Main St");
        Admin admin = new Admin();
        admin.setName("Jane Admin");
        admin.setPhone("0987654321");
        admin = courierRepository.save(admin);

        deliveryService.assignOrderToCourier(order.getOrderId(), admin.getCourierId());

        Admin updatedCourier = courierRepository.findById(admin.getCourierId()).orElse(null);
        assertNotNull(updatedCourier);
        assertFalse(updatedCourier.getCurrentRoute().isEmpty());
    }

    @Test
    void testTrackOrder() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer = customerRepository.save(customer);

        Product product = new Product();
        product.setName("Book");
        product.setQuantity(2);
        product.setPrice(10.0);

        Order order = deliveryService.createOrder(customer, List.of(product), "123 Main St");
        String status = deliveryService.trackOrder(order.getOrderId());
        assertEquals("Processing", status);

        String nonExistentStatus = deliveryService.trackOrder(999L);
        assertEquals("Order not found", nonExistentStatus);
    }
}