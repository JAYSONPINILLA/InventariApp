package com.erp.inventariapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.inventariapp.Entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
