package com.erp.inventariapp.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.erp.inventariapp.Entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
