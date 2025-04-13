package com.example.miniproject2.repositories;

import com.example.miniproject2.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customers by email domain (e.g., @gmail.com)
    List<Customer> findByEmailEndingWith(String domain);

    // Find customers by phone number prefix (e.g., starts with 010)
    List<Customer> findByPhoneNumberStartingWith(String prefix);
}