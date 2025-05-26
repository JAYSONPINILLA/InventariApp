package com.erp.inventariapp.DTOs;

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

    @NotBlank
    private String role;

    @NotNull
    private Boolean state;

    private Long idperson;   
    private String typeId;   
    private String identification;   
    private String name;   
}
