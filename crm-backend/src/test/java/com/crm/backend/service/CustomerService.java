package com.crm.backend.service;

import com.crm.backend.model.Customer;
import com.crm.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Update customer by ID
    public Customer updateCustomer(Long id, Customer updatedData) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(updatedData.getName());
            customer.setEmail(updatedData.getEmail());
            customer.setPhone(updatedData.getPhone());
            return customerRepository.save(customer);
        }).orElse(null);
    }

    // Delete customer by ID
    public boolean deleteCustomer(Long id) {
        return customerRepository.findById(id).map(customer -> {
            customerRepository.delete(customer);
            return true;
        }).orElse(false);
    }
}
