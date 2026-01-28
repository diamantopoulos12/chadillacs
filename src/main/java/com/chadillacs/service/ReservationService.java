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
import java.util.Objects;
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
     * 1. Find all reservations //TODO optimize to find only reservations with that vehicle
     * 2. Loop through reservations until we find a reservation that matches this vehicle
     * 3. Check there are no date conflicts
     * 4. Hmm.
     * @param reqVehicle
     * @param reqDate
     * @param reqDays
     * @return boolean
     */
    public Boolean validateVehicleIsAvailable(Vehicle reqVehicle, LocalDate reqDate, Integer reqDays) {

        Boolean isValidated = true;

        logger.info("Checking if we can reserve vehicle: " + reqVehicle.toString());

        Iterable<Reservation> reservationList = reservationRepository.findAll();
        logger.info("Scanning current reservations: " + reservationList.toString());
        for (Reservation reservation : reservationList) {
            logger.info("Checking if reservation is linked to requested vehicle: " + reservation.toString());

            if (Objects.equals(reqVehicle.getId(), reservation.getVehicle().getId())) {
                logger.info("Found existing vehicle reservation: " + reservation.toString());

                //Now we need to calculate the current reservation start date plus number of days
                //And compare that to the requested reservation startDate and number of days
                //If there is a conflict we need to set isValidated to false and deny the reservation
                LocalDate resDate = reservation.getStartDate();
                Integer resDays = reservation.getNumDays();
                logger.info("Requested date: " + reqDate + " for number of days: " + reqDays);
                logger.info("Reservation date: " + resDate + " for number of days: " + resDays);
                logger.info("How do I check if these overlap?");

            } else {
                logger.info("This reservation is for another vehicle: " + reservation.toString());
            }

            //TODO this is where we need to fix next


        }


        return isValidated;
    }

}
