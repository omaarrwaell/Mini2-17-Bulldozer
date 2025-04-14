package com.example.miniproject2.services;

import com.example.miniproject2.models.Payment;
import com.example.miniproject2.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }


    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }


    public Payment updatePayment(Long id, Payment payment) {
        if (paymentRepository.existsById(id)) {
            payment.setId(id); // Ensure ID is set correctly
            return paymentRepository.save(payment);
        }
        return null; // Or throw exception - Payment not found
    }


    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }


    public List<Payment> findPaymentsByTripId(Long tripId) {
        return paymentRepository.findByTripId(tripId);
    }


    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}