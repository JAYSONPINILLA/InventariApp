package com.erp.inventariapp.ServicesInterfaces;

import java.util.List;

import com.erp.inventariapp.DTOs.PersonDTO;

public interface IPersonService {
    public List<PersonDTO> findAll();
    public PersonDTO findById(Long idperson);
    public PersonDTO create(PersonDTO dto);
    public PersonDTO update(Long id, PersonDTO dto);
    public void delete(Long idperson);
}
