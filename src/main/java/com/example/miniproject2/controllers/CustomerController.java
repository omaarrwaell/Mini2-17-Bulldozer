package com.example.miniproject2.controllers;

import com.example.miniproject2.models.Customer;
import com.example.miniproject2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        if (customer == null || customer.getEmail() == null) {
            return null;
        }
        return customerService.addCustomer(customer);
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers != null ? customers : Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return customerService.getCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (customer == null ) {
            return null;
        }
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return "Invalid customer ID.";
        }
        customerService.deleteCustomer(id);
        return "Customer with ID " + id + " deleted successfully.";
    }

    @GetMapping("/findByEmailDomain")
    public List<Customer> findCustomersByEmailDomain(@RequestParam String domain) {
        if (domain == null || domain.isEmpty()) {
            return Collections.emptyList();
        }
        return customerService.findCustomersByEmailDomain(domain);
    }

    @GetMapping("/findByPhonePrefix")
    public List<Customer> findCustomersByPhonePrefix(@RequestParam String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return Collections.emptyList();
        }
        return customerService.findCustomersByPhonePrefix(prefix);
    }
}
