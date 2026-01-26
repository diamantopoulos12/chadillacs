package com.chadillacs.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

	@RequestMapping("/vehicle")
	String vehicle() {
		return "This is the vehicle\n";
	}

}
