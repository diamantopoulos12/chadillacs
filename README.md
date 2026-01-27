## Chadillacs! Car rentals!

This is a car reservation system using some object oriented principles. The
requirements can be seen in the Requirements.md. This will be implemented
as a java spring boot app that will have a small database backend with a
couple of REST interfaces to list available vehicles in the inventory, store
customer data, and create a reservation for a customer.

### Design

- Follow the basic getting started "first application" guide from the spring boot
  website (in reference).

- There are 3 main entities - vehicle, customer, and reservation. For the customer and vehicle objects there is a
  controller for each that handles requests. The controllers call into the service to handle business logic and
  there is a repository interface which manages the data JPA.

- Start with a H2 in memory db for simplicity. Moving to MySQL would be better.

- Implement a couple of basic REST APIs to do some operations on the data.

- Containerize the app for easy deployment. Or don't :).


### System Requirements

Java 17+ and Maven 3.6.3+

### Quickstart

Checkout code and run:

```bash
git clone git@github.com:diamantopoulos12/chadillacs.git
mvn spring-boot:run
mvn test
```

In the CarRentalApplication.java 2 mock vehicles, 2 mock customers, and
1 mock reservation is created and can be seen in the h2-console.

H2-Console (User Name: sa, Password:<blank>, JDBC URL: jdbc:h2:mem:testdb):

http://localhost:8081/h2-console/

Sort of interesting endpoint that calls into the createReservation service:

```bash
curl http://0.0.0.0:8081/api/reservations/test-create-reservation
```

Less interesting endpoints:

```
curl http://0.0.0.0:8081/api/vehicle
curl http://0.0.0.0:8081/api/vehicles
curl http://0.0.0.0:8081/api/customer
curl http://0.0.0.0:8081/api/customers
```

### Unit Tests

#### test_reservation_getAllReservations()

Calls into ReservationService.getAllReservations to verify it returns.   

#### test_reservation_createReservation()

Calls into ReservationService.createReservation to verify creating a reservation.
Need to fix the validation to correctly verify the vehicle is available.

### Out of scope

What I probably won't tackle is any type authentication. We would
probably want an admin interface to update vehicle inventory and user logins
for actually making reservations.

### Reference

https://docs.spring.io/spring-boot/tutorial/first-application/index.html
