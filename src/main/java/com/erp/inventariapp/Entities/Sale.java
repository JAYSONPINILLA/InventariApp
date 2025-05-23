package com.erp.inventariapp.Entities;

import java.util.Date;

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
@Table(name="Sale")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsale;

    @Column(nullable=false)
    private Date date;

    @Column(nullable=false)
    private Double amount;

    @Column(nullable=false)
    private Double discount;

    @Column(nullable=false)
    private Double tax;

    @Column(nullable=false)
    private Double billing;

    @Column(nullable=false)
    private Boolean state;

    @Column(length=250, nullable=false)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "idcustomer", referencedColumnName = "idcustomer")
    private Customer customer;
}
