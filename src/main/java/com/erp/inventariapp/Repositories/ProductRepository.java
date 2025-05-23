package com.erp.inventariapp.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.erp.inventariapp.Entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.code Like CONCAT('%', :code, '%')")
    List<Product> findByCodeLike(@Param("code") String code);

    @Query("SELECT p FROM Product p WHERE p.name Like CONCAT('%', :name, '%')")
    List<Product> findByNameLike(@Param("name") String code);

    @Query("SELECT p FROM Product p WHERE p.category.idcategory = :idcategory")
    List<Product> findByCategoryId(@Param("idcategory") Long idcategory);

    @Query("SELECT p FROM Product p, Category c WHERE p.category.idcategory = c.idcategory And c.name LIKE CONCAT('%', :categoryname, '%')")
    List<Product> findByCategoryName(@Param("categoryname") String categoryname);    

}
