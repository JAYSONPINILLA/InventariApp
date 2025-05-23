package com.erp.inventariapp.ServicesInterfaces;

import java.util.List;

import com.erp.inventariapp.DTOs.CategoryDTO;

public interface ICategoryService {

    public List<CategoryDTO> findAll();
    public CategoryDTO findById(Long idcategory);
    public CategoryDTO create(CategoryDTO dto);
    public CategoryDTO update(Long id, CategoryDTO dto);
    public void delete(Long idcategory);
}
