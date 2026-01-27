package com.chadillacs.controller;

import com.chadillacs.model.Customer;
import com.chadillacs.service.CustomerService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

	@RequestMapping("/customer")
	String customer() {
		return "/customer handler\n";
	}


    @RequestMapping("/customers")
    String customers() {

        List<Customer> customerList = customerService.getAllCustomers();

        for (Customer customer : customerList) {
            System.out.println("found customer - last name = " + customer.getLastName());
            logger.info("found customer - last name = " + customer.getLastName());
        }

        return "Number of customers we found: " + customerList.size() + "\n";
    }

}
