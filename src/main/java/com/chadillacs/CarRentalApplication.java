package com.chadillacs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
