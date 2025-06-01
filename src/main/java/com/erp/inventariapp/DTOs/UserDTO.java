package com.erp.inventariapp.DTOs;

import com.erp.inventariapp.Enums.RoleUserEnum;

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

    private PersonDTO person;

}
