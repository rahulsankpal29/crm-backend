package com.crm.backend.controller;

import com.crm.backend.model.Customer;
import com.crm.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ✅ Home route - to avoid Whitelabel error
    @GetMapping("/")
    public String home() {
        return "CRM Backend is Running!";
    }

    @PostMapping("/api/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping("/api/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/api/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updated = customerService.updateCustomer(id, customer);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id)
                ? ResponseEntity.ok("Deleted successfully.")
                : ResponseEntity.notFound().build();
    }
}
