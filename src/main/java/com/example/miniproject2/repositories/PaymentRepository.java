package com.example.miniproject2.repositories;

import com.example.miniproject2.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Find payment by trip ID
    List<Payment> findByTripId(Long tripId);

    // Find payments with amount greater than threshold
    List<Payment> findByAmountGreaterThan(Double threshold);
}