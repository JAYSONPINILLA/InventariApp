package com.erp.inventariapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.erp.inventariapp.Entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
