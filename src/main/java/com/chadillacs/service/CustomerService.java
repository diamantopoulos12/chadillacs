package com.chadillacs.service;

import com.chadillacs.model.Customer;
import com.chadillacs.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        Iterable<Customer> customerIterable = customerRepository.findAll();

        for (Customer customer : customerIterable) {
            customerList.add(customer);
        }

        return customerList;
    }




}
