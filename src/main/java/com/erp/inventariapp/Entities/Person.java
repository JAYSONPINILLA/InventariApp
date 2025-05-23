package com.erp.inventariapp.Entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Entity
@Table(name="Person", uniqueConstraints = {
                @UniqueConstraint(columnNames = {"typeId", "identification"})
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idperson;

    @Column(length=2, nullable=false)
    private String typeId;  //NI, CC, CE, PS, TI, RC
    
    @Column(length=13, nullable=false)
    private String identification;
    
    @Column(length=100, nullable=false)
    private String name;

    @Column(length=200, nullable=false)
    private String adress;

    @Column(length=200, nullable=false)
    private String email;
    
    @Column(length=10, nullable=false)
    private String phone;

    @Column(nullable=false)
    private Date birthdate;

    @Column(nullable=false)
    private Boolean genre;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Customer customer;
    
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Seller seller;
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;      
    
}