package com.erp.inventariapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.inventariapp.Entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
