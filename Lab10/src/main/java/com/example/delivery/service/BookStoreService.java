package com.example.bookstore.service;

import com.example.bookstore.model.*;
import com.example.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CourierRepository courierRepository;

    public Order createOrder(Customer customer, List<Product> items, String deliveryAddress) {
        Customer savedCustomer = customerRepository.save(customer);
        Order order = new Order();
        order.setCustomer(savedCustomer);
        order.setItems(items);
        order.setDeliveryAddress(deliveryAddress);
        order.setStatus("Processing");
        return orderRepository.save(order);
    }

    public void assignOrderToCourier(Long orderId, Long courierId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Admin> admin = courierRepository.findById(courierId);

        if (order.isPresent() && admin.isPresent()) {
            List<Order> route = admin.get().getCurrentRoute();
            route.add(order.get());
            admin.get().setCurrentRoute(route);
            courierRepository.save(admin.get());
        }
    }

    public String trackOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(Order::getStatus)
                .orElse("Order not found");
    }

    public void updateOrderStatus(Long orderId, String status) {
        orderRepository.findById(orderId)
                .ifPresent(order -> {
                    order.setStatus(status);
                    orderRepository.save(order);
                });
    }

    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }
}