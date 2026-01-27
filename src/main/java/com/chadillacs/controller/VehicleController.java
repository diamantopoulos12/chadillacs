package com.chadillacs.controller;

import com.chadillacs.model.Vehicle;
import com.chadillacs.service.VehicleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping("/vehicle")
    String vehicle() {
        return "This is the vehicle\n";
    }

    @RequestMapping("/vehicles")
    String vehicles() {

        List<Vehicle> vehicleList = vehicleService.getAllVehicles();

        return "Number of vehicles we found: " + vehicleList.size() + "\n";
    }
}
