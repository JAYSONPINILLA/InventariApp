package com.erp.inventariapp.ServicesInterfaces;

import java.util.List;

import com.erp.inventariapp.DTOs.ProductDTO;

public interface IProductService {

    public List<ProductDTO> findAll();
    public ProductDTO findById(Long idproduct);
    public List<ProductDTO> findByCodeLike(String code);
    public List<ProductDTO> findByNameLike(String name);
    public List<ProductDTO> findByCategoryId(Long idcategory);
    public List<ProductDTO> findByCategoryName(String categoryname);
    public ProductDTO create(ProductDTO product);
    public ProductDTO update(Long idproducto, ProductDTO product);
    public void delete(Long idproduct);
}
