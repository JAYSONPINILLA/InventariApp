package com.erp.inventariapp.DTOs;

import java.time.LocalDate;

import com.erp.inventariapp.Enums.GenreEnum;
import com.erp.inventariapp.Enums.TypeIdEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long idcustomer;

    private Boolean state;

    //private PersonDTO person;

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

