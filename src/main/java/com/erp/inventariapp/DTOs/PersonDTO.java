package com.erp.inventariapp.DTOs;

import java.time.LocalDate;
import java.util.List;

import com.erp.inventariapp.Entities.Customer;
import com.erp.inventariapp.Entities.Seller;
import com.erp.inventariapp.Enums.GenreEnum;
import com.erp.inventariapp.Enums.TypeIdEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long idperson;

    private TypeIdEnum typeId; 
    
    private String identification;
   
    private String name;

    private String adress;

    private String email;
    
    private String phone;

    private LocalDate birthdate;

    private GenreEnum genre;
}
