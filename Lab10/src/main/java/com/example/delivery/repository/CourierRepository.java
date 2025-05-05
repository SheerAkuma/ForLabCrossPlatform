package com.example.bookstore.repository;

import com.example.bookstore.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Admin, Long> {
}