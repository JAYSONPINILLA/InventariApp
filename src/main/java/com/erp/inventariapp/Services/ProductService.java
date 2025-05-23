package com.erp.inventariapp.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.ProductDTO;
import com.erp.inventariapp.Entities.Product;
import com.erp.inventariapp.Repositories.CategoryRepository;
import com.erp.inventariapp.Repositories.MeasurementRepository;
import com.erp.inventariapp.Repositories.ProductRepository;
import com.erp.inventariapp.ServicesInterfaces.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productrepository;

    @Autowired
    CategoryRepository categoryrepository;
    
    @Autowired
    MeasurementRepository measurementrepository;
    
    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = (List<Product>) productrepository.findAll();
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(p -> productsDTO.add(this.convertToDTO(p)));
        return productsDTO;
    }

    @Override
    public ProductDTO findById(Long idproduct) {
        Product p = productrepository.findById(idproduct).get();
        return (this.convertToDTO(p)); 
    }

    @Override
    public List<ProductDTO> findByCodeLike(String code) {
        List<Product> products = (List<Product>) productrepository.findByCodeLike(code);
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(p -> productsDTO.add(this.convertToDTO(p)));
        return productsDTO;
    }

    @Override
    public List<ProductDTO> findByNameLike(String name) {
        List<Product> products = (List<Product>) productrepository.findByNameLike(name);
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(p -> productsDTO.add(this.convertToDTO(p)));
        return productsDTO;
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long idcategory) {
        List<Product> products = (List<Product>) productrepository.findByCategoryId(idcategory);
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(p -> productsDTO.add(this.convertToDTO(p)));
        return productsDTO;
    }

    @Override
    public List<ProductDTO> findByCategoryName(String categoryname) {
        List<Product> products = (List<Product>) productrepository.findByCategoryName(categoryname);
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(p -> productsDTO.add(this.convertToDTO(p)));
        return productsDTO;
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product p = new Product();
        return saveOrUpdate(p, dto);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product p = productrepository.findById(id).get();
        return saveOrUpdate(p, dto);
    }

    @Override
    public void delete(Long idproduct) {
        //if (!productrepository.existsById(idproduct))
        //    throw ResourceNotFoundException("Producto no encontrado");
        productrepository.deleteById(idproduct);
    }

    private ProductDTO saveOrUpdate(Product p, ProductDTO dto) {
        p.setCode(dto.getCode());
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        p.setStockmin(dto.getStockmin());
        p.setStockmax(dto.getStockmax());
        p.setState(dto.getState());
        p.setCategory(categoryrepository.findById(dto.getCategoryId()).get());
        p.setMeasurement(measurementrepository.findById(dto.getMesuarementId()).get());
        
        return convertToDTO(productrepository.save(p));
    }

    private ProductDTO convertToDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setIdproduct(p.getIdproduct());
        dto.setCode(p.getCode());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setStock(p.getStock());
        dto.setStockmin(p.getStockmin());
        dto.setStockmax(p.getStockmax());
        dto.setState(p.getState());
        dto.setCategoryId(p.getCategory().getIdcategory());
        dto.setMesuarementId(p.getMeasurement().getIdmeasurement());
        return dto;
    }
}
