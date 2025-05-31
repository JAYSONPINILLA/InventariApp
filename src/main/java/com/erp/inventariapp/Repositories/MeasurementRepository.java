package com.erp.inventariapp.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.inventariapp.Entities.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Page<Measurement> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
