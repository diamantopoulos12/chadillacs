package com.chadillacs.service;

import com.chadillacs.model.Customer;
import com.chadillacs.model.Reservation;
import com.chadillacs.model.Vehicle;
import com.chadillacs.model.VehicleType;
import com.chadillacs.repository.CustomerRepository;
import com.chadillacs.repository.ReservationRepository;
import com.chadillacs.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Customer testCustomer;
    private Vehicle testVehicle;
    private Reservation testReservation;

    @BeforeEach
    void setUp() {

        testCustomer = new Customer("Pink", "Panther");
        testCustomer.setPhoneNumber("867-5309");

        testVehicle = new Vehicle();
        testVehicle.setType(VehicleType.SEDAN);
        testVehicle.setMake("Nissan");
        testVehicle.setModel("Maxima");
        testVehicle.setModelYear("2006");

        testReservation = new Reservation();
        testReservation.setCustomer(testCustomer);
        testReservation.setVehicle(testVehicle);
        testReservation.setStartDate(LocalDate.now());
        testReservation.setNumDays(11);

    }

    @Test
    void test_reservation_getAllReservation() {

        List<Reservation> mockReservations = List.of(testReservation);
        when(reservationRepository.findAll()).thenReturn(mockReservations);

        List<Reservation> result = reservationService.getAllReservations();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pink", result.get(0).getCustomer().getFirstName());
    }

    @Test
    void test_reservation_createReservation() {

        List<Reservation> mockReservations = List.of(testReservation);
        when(reservationRepository.findAll()).thenReturn(mockReservations);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(vehicleRepository.findAll()).thenReturn(List.of(testVehicle));


        Reservation result = reservationService.createReservation(1L, LocalDate.now(), 2, VehicleType.SEDAN);

        System.out.println("Result: " + result);

        assertNotNull(result);

    }

}