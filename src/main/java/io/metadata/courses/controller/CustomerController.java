package io.metadata.courses.controller;

import io.metadata.courses.model.Customer;
import io.metadata.courses.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/add-customer")
    public Customer addTradie(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/get-all-customer")
    public List<Customer> getAllTradies() {
        return customerRepository.findAll();
    }
}
