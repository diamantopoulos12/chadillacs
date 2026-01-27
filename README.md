## Chadillacs! Car rentals!

This is a car reservation system using some object oriented principles. The
requirements can be seen in the Requirements.md. This will be implemented
as a java spring boot app that will have a small database backend with a
couple of REST interfaces to list available vehicles in the inventory, store
customer data, and create a reservation for a customer.


### Design

- Follow the basic getting started "first application" guide from the spring boot
website (in reference).

- There 3 main entities - vehicle, customer, and reservation. For the customer and vehicle objects there is a
  controller for each that handles requests. The controllers call into the service to handle business logic and
  there is a repository interface which manages the data JPA.

- Start with a H2 in memory db for simplicity. Moving to MySQL would be better.

- Implement a couple of basic REST APIs to do some operations on the data.

- Containerize the app for easy deployment. Or don't :). 


### Out of scope

What I probably won't tackle is any type authentication. We would
probably want an admin interface to update vehicle inventory and user logins
for actually making reservations.

### Reference

https://docs.spring.io/spring-boot/tutorial/first-application/index.html
