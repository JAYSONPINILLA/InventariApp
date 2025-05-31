package com.erp.inventariapp.Auth;

import com.erp.inventariapp.DTOs.PersonDTO;
import com.erp.inventariapp.Enums.RoleUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    RoleUserEnum roleUser;
    Boolean state;
    PersonDTO person;
}
