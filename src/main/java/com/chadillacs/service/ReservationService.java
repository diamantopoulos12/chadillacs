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

    private static final Logger logger = Logger.getLogger(ReservationService.class.toString());

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
            logger.info("Checking vehicle: " + vehicle);
            if (vehicle.getType().equals(vehicleType)) {
                logger.info("Found matching vehicle type - id: " + vehicle.getId());

                //TODO need to now do a final validation that the vehicle isn't already reserved
                if (this.validateVehicleIsAvailable(vehicle, startDate, numDays)) {
                    logger.info("Making reservation");
                    //Reserve the vehicle
                    reservation = new Reservation();
                    reservation.setVehicle(vehicle);
                    reservation.setCustomer(customer.orElse(null));
                    reservation.setStartDate(startDate);
                    reservation.setNumDays(numDays);
                    reservationRepository.save(reservation);
                    logger.info("Successfully created reservation");
                    break;
                } else {
                    logger.info("Oops this vehicle is reserved those days - keep searching");
                }
            }
        }

        if (reservation == null) {
            Logger.getLogger(ReservationService.class.toString()).severe("Error creating reservation");
        }
        return reservation;
    }

    /**
     * Return true if vehicle is available to be rented.
     * @param vehicle
     * @param date
     * @param days
     * @return boolean
     */
    public Boolean validateVehicleIsAvailable(Vehicle vehicle, LocalDate date, Integer days) {

        Boolean isValidated = true;

        logger.info("Checking if we can reserve vehicle: " + vehicle.toString());

        Iterable<Reservation> reservationList = reservationRepository.findAll();
        logger.info("Scanning current reservations: " + reservationList.toString());
        for (Reservation reservation : reservationList) {
            logger.info("Existing reservations loop - reservation: " + reservation.toString());

            //TODO this is where we need to fix next


        }


        return isValidated;
    }

}
