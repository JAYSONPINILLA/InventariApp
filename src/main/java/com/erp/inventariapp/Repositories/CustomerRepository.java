package com.erp.inventariapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.inventariapp.Entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

