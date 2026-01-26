package com.chadillacs;

import com.chadillacs.model.Customer;
import com.chadillacs.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
public class CarRentalApplication {

	@RequestMapping("/")
	String home() {
		return "Chadillacs Car Rental Application!\n";
	}

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
                System.out.println("Test startup message");
	}

    @Bean
    CommandLineRunner initCustomerMockData(CustomerRepository customerRepository) {
        return args -> {
            System.out.println("Populate customer table with mock data");
            Customer cust1 = new Customer();
            cust1.setFirstName("Zdeno");
            cust1.setLastName("Chara");
            cust1.setPhoneNumber("6171110001");

            Customer cust2 = new Customer();
            cust2.setFirstName("Drake");
            cust2.setLastName("Maye");
            cust2.setPhoneNumber("6171110002");

            customerRepository.save(cust1);
            customerRepository.save(cust2);

            //Customer whoami = customerRepository.findById(cust1.getId());
            //Optional<Customer> whoami = customerRepository.findById(cust1.getId());
            //System.out.println("Lookup from DB returned: " + whoami.get().getFirstName());

            Iterable<Customer> customerList = customerRepository.findAll();
            for (Customer customer : customerList){
                System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName() + " ID: " + customer.getId());
            }

        };
    }
}
