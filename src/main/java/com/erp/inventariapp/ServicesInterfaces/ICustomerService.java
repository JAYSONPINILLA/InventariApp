package com.erp.inventariapp.ServicesInterfaces;

import java.util.List;

import com.erp.inventariapp.DTOs.CustomerDTO;

public interface ICustomerService {
    public List<CustomerDTO> findAll();
    public CustomerDTO findById(Long idcustomer);
    public CustomerDTO create(CustomerDTO dto);
    public CustomerDTO update(Long id, CustomerDTO dto);
    public void delete(Long idcustomer);
}
