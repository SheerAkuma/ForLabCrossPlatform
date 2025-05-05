package com.example.bookstore.repository;

import com.example.bookstore.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourierRepositoryTest {

    @Autowired
    private CourierRepository courierRepository;

    @Test
    void testSaveAndFindCourier() {
        Admin admin = new Admin();
        admin.setName("Mike Admin");
        admin.setPhone("5555555555");

        Admin savedCourier = courierRepository.save(admin);
        assertNotNull(savedCourier.getCourierId());

        Admin foundCourier = courierRepository.findById(savedCourier.getCourierId()).orElse(null);
        assertNotNull(foundCourier);
        assertEquals("Mike Admin", foundCourier.getName());
        assertEquals("5555555555", foundCourier.getPhone());
    }
}