package com.chadillacs;

import com.chadillacs.model.Customer;
import com.chadillacs.model.Reservation;
import com.chadillacs.model.Vehicle;
import com.chadillacs.model.VehicleType;
import com.chadillacs.repository.CustomerRepository;
import com.chadillacs.repository.ReservationRepository;
import com.chadillacs.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    //Initialize some mock customer data
    @Bean
    CommandLineRunner initCustomerMockData(CustomerRepository customerRepository) {
        return args -> {
            System.out.println("Populate customer table with mock data");
            Customer cust1 = new Customer();
            cust1.setFirstName("Zdeno");
            cust1.setLastName("Chara");
            cust1.setPhoneNumber("6171110001");

            Customer cust2 = new Customer();
            cust2.setFirstName("Drake");
            cust2.setLastName("Maye");
            cust2.setPhoneNumber("6171110002");

            customerRepository.save(cust1);
            customerRepository.save(cust2);

            //Customer whoami = customerRepository.findById(cust1.getId());
            //Optional<Customer> whoami = customerRepository.findById(cust1.getId());
            //System.out.println("Lookup from DB returned: " + whoami.get().getFirstName());

            Iterable<Customer> customerList = customerRepository.findAll();
            for (Customer customer : customerList){
                System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName() + " ID: " + customer.getId());
            }

        };
    }

    //Initialize some mock vehicle data
    @Bean
    CommandLineRunner initVehicleMockData(VehicleRepository vehicleRepository) {
        return args -> {

            Vehicle vehicle1 = new Vehicle();
            vehicle1.setType(VehicleType.SUV);
            vehicle1.setMake("Volkswagen");
            vehicle1.setModel("Tiguan");
            vehicle1.setModelYear("2021");
            vehicleRepository.save(vehicle1);

            Vehicle vehicle2 = new Vehicle();
            vehicle2.setType(VehicleType.SUV);
            vehicle2.setMake("Jeep");
            vehicle2.setModel("Wrangler");
            vehicle2.setModelYear("2010");
            vehicleRepository.save(vehicle2);

        };
    }

    //Initialize a fake reservation
    @Bean
    CommandLineRunner initReservationMockData(ReservationRepository reservationRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        return args -> {

            //Case 1 Rent Zdeno the first vehicle we find.
            Customer customerToRent = customerRepository.findByLastName("Chara");

            Vehicle vehicleToRent = null;
            Iterable<Vehicle> vehicleIterable = vehicleRepository.findAll();

            for (Vehicle vehicle : vehicleIterable) {
                vehicleToRent = vehicle;
                break;
            }

            Reservation myReservation = new Reservation();
            myReservation.setCustomer(customerToRent);
            myReservation.setVehicle(vehicleToRent);
            myReservation.setStartDate(LocalDate.now());
            myReservation.setNumDays(3);
            reservationRepository.save(myReservation);

        };
    }



}
