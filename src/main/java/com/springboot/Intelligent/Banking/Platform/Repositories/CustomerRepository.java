package com.springboot.Intelligent.Banking.Platform.Repositories;

import com.springboot.Intelligent.Banking.Platform.Entities.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerNumber(Long customerNumber);
}
