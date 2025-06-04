package com.erp.inventariapp.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.inventariapp.Entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("""
        SELECT s
        FROM Seller s
        WHERE s.person.name LIKE CONCAT('%', :name, '%')
    """)
    Optional<List<Seller>> findByNameContainingIgnoreCase(String name);
}
