package com.crm.backend.controller;

import com.crm.backend.model.Customer;
import com.crm.backend.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long testCustomerId;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll(); // clean DB before each test

        // Save a sample customer for GET, PUT, DELETE tests
        Customer customer = new Customer();
        customer.setName("Rahul");
        customer.setEmail("rahul@email.com");
        customer.setPhone("9876543210");

        testCustomerId = customerRepository.save(customer).getId();
    }

    @Test
    public void testCreateCustomer() throws Exception {
        String customerJson = "{\"name\":\"Amit\",\"email\":\"amit@email.com\",\"phone\":\"9999999999\"}";

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/api/customers/" + testCustomerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rahul"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("Rahul Updated");
        updatedCustomer.setEmail("new@email.com");
        updatedCustomer.setPhone("1234567890");

        String json = objectMapper.writeValueAsString(updatedCustomer);

        mockMvc.perform(put("/api/customers/" + testCustomerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rahul Updated"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/customers/" + testCustomerId))
                .andExpect(status().isOk());

        // Try getting deleted customer
        mockMvc.perform(get("/api/customers/" + testCustomerId))
                .andExpect(status().isNotFound());
    }
}
