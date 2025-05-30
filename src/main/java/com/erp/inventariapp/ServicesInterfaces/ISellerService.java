package com.erp.inventariapp.ServicesInterfaces;

import java.util.List;

import com.erp.inventariapp.DTOs.SellerDTO;

public interface ISellerService {
    public List<SellerDTO> findAll();
    public SellerDTO findById(Long idseller);
    public SellerDTO create(SellerDTO dto);
    public SellerDTO update(Long id, SellerDTO dto);
    public void delete(Long idseller);
}
