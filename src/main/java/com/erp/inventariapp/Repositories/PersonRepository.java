package com.erp.inventariapp.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.erp.inventariapp.Entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
