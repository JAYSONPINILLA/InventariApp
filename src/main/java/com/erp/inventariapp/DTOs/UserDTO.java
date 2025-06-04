package com.erp.inventariapp.DTOs;

import java.time.LocalDate;

import com.erp.inventariapp.Enums.GenreEnum;
import com.erp.inventariapp.Enums.RoleUserEnum;
import com.erp.inventariapp.Enums.TypeIdEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long iduser;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    //@NotBlank
    private RoleUserEnum roleUser;

    @NotNull
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
