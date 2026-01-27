package com.chadillacs.service;

import com.chadillacs.model.Customer;
import com.chadillacs.model.Reservation;
import com.chadillacs.model.Vehicle;
import com.chadillacs.model.VehicleType;
import com.chadillacs.repository.CustomerRepository;
import com.chadillacs.repository.ReservationRepository;
import com.chadillacs.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ReservationService {

    //These are how we access the data in the db
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public ReservationService(ReservationRepository reservationRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Reservation> getAllReservations() {

        List<Reservation> reservationList = new ArrayList<>();
        Iterable<Reservation> reservationIterable = reservationRepository.findAll();

        for (Reservation reservation : reservationIterable) {
            reservationList.add(reservation);
        }

        return reservationList;
    }

    /**
     * The heart of it all. Creating a reservation!
     * @param customerId
     * @param startDate
     * @param numDays
     * @param vehicleType
     * @return
     */
    public Reservation createReservation(Long customerId, LocalDate startDate, Integer numDays, VehicleType vehicleType) {

        Reservation reservation = null;

        //Find the customer
        Optional<Customer> customer = customerRepository.findById(customerId);

        //Find all the vehicles. Then search for the right type and validate its available.
        //Probably should optimize search based on Type so its faster.
        Iterable<Vehicle> vehicleList = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicleList) {
            System.out.println("Checking vehicle: " + vehicle);
            if (vehicle.getType().equals(vehicleType)) {
                System.out.println("Found matching vehicle type - id: " + vehicle.getId());
                //Reserve the vehicle
                reservation = new Reservation();
                reservation.setVehicle(vehicle);
                reservation.setCustomer(customer.orElse(null));
                reservation.setStartDate(startDate);
                reservation.setNumDays(numDays);
                reservationRepository.save(reservation);
                Logger.getLogger(ReservationService.class.toString()).info("Successfully created reservation");
                //TODO need to know do a final validation that the vehicle isn't already reserved
            }
        }

        if (reservation == null) {
            Logger.getLogger(ReservationService.class.toString()).severe("Error creating reservation");
        }
        return reservation;
    }
}
