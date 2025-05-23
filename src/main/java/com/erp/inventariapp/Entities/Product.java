package com.erp.inventariapp.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduct;

    @Column(length=10, nullable=false, unique=true)
    private String code;

    @Column(length=50, nullable=false)
    private String name;

    @Column(nullable=false)
    private Double price;

    @Column(nullable=false)
    private Double stock;

    @Column(nullable=false)
    private Double stockmin;

    @Column(nullable=false)
    private Double stockmax;

    @Column(nullable=false)
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    private Category category;    

    @ManyToOne
    @JoinColumn(name = "idmeasurement", referencedColumnName = "idmeasurement")
    private Measurement measurement;  
}
