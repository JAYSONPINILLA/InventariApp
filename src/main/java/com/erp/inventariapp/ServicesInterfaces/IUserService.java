package com.erp.inventariapp.ServicesInterfaces;

import java.util.List;

import com.erp.inventariapp.DTOs.UserDTO;

public interface IUserService {
    public List<UserDTO> findAll();
    public UserDTO findById(Long iduser);
    public List<UserDTO> findByName(String name);
    public UserDTO create(UserDTO dto);
    public UserDTO update(Long id, UserDTO dto);
    public void delete(Long iduser);
}
