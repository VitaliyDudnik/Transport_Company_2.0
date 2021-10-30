package com.example.transport_company.repository;

import com.example.transport_company.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findById(long id);

    Customer findByEmail(String email);
}
