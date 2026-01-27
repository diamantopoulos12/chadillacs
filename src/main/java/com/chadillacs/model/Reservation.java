package com.chadillacs.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Add customer join
    @ManyToOne
    private Customer customer;

    //Add vehicle join
    @ManyToOne
    private Vehicle vehicle;

    //Essential details of the reservation - use this to validate creating reservations.
    private LocalDate startDate;
    private Integer numDays;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getNumDays() {
        return numDays;
    }

    public void setNumDays(Integer numDays) {
        this.numDays = numDays;
    }

    public void setId(long l) {
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                ", startDate=" + startDate +
                ", numDays=" + numDays +
                '}';
    }
}
