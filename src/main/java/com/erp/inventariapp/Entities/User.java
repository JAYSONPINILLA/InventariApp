package com.erp.inventariapp.Entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;
    
    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(length=2, nullable=false)
    private String role;        //AD=Admin, SE=Seller

    @Column(nullable=false)
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "idperson", referencedColumnName = "idperson")
    private Person person;    
}
