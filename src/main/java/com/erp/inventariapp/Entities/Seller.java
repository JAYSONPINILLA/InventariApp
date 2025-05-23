package com.erp.inventariapp.Entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="Seller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idseller;

    @Column(nullable=false)
    private Boolean state;

    @OneToOne
    @JoinColumn(name = "idperson", referencedColumnName = "idperson")
    private Person person;
}
