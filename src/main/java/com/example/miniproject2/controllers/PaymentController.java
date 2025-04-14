package com.example.miniproject2.controllers;

import com.example.miniproject2.models.Payment;
import com.example.miniproject2.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment) {
        if (payment == null || payment.getAmount() == null || payment.getPaymentMethod() == null) {
            return null;
        }
        return paymentService.addPayment(payment);
    }

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return payments != null ? payments : Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/update/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        if (payment == null || payment.getAmount() == null) {
            return null;
        }
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return "Invalid payment ID.";
        }
        paymentService.deletePayment(id);
        return "Payment with ID: " + id + " has been deleted successfully";
    }

    @GetMapping("/findByTripId")
    public List<Payment> findPaymentsByTripId(@RequestParam Long tripId) {
        if (tripId == null || tripId <= 0) {
            return Collections.emptyList();
        }
        List<Payment> payments = paymentService.findPaymentsByTripId(tripId);
        return payments != null ? payments : Collections.emptyList();
    }

    @GetMapping("/findByAmountThreshold")
    public List<Payment> findPaymentsWithAmountGreaterThan(@RequestParam Double threshold) {
        if (threshold == null || threshold < 0) {
            return Collections.emptyList();
        }
        List<Payment> payments = paymentService.findByAmountThreshold(threshold);
        return payments != null ? payments : Collections.emptyList();
    }
}
