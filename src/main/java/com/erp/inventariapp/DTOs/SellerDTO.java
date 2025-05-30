package com.erp.inventariapp.DTOs;

import com.erp.inventariapp.Entities.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {
    private Long idseller;

    private Boolean state;

    private Person person;
}
