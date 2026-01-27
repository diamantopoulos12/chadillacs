package com.chadillacs.service;

import com.chadillacs.model.Vehicle;
import com.chadillacs.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {

        List<Vehicle> vehicleList = new ArrayList<>();
        Iterable<Vehicle> vehicleIterable = vehicleRepository.findAll();

        for (Vehicle vehicle : vehicleIterable) {
            vehicleList.add(vehicle);
        }

        System.out.println("exiting getAllVehicles service method");
        return vehicleList;
    }
}
