package com.chadillacs.repository;

import com.chadillacs.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    //Custom data queries.
    Customer findByLastName(String lastName);

}
