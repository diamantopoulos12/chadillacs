package com.chadillacs.controller;

import com.chadillacs.model.VehicleType;
import com.chadillacs.service.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping("/create")
    String create() {

        System.out.println("Received create reservation request");

        return "Create a reservation\n";
    }

    @RequestMapping("/list")
    String list() {

        System.out.println("List reservations");


        return "List reservations: \n";
    }

    //Just a test
    @RequestMapping("/test-create-reservation")
    String test_create_reservation() {

        reservationService.createReservation(10L, LocalDate.now(), 4, VehicleType.SUV);

        return "test-create-reservation\n";
    }


}
