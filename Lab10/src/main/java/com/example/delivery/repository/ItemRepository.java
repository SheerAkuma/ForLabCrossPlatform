package com.example.bookstore.repository;

import com.example.bookstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Product, Long> {
}