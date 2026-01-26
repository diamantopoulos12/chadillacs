package com.chadillacs.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

	@RequestMapping("/reservation")
	String reservation() {
		return "This is the reservation\n";
	}

}
