package com.erp.inventariapp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.inventariapp.DTOs.ProductDTO;
import com.erp.inventariapp.Services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name="Products")
public class ProductController {
    @Autowired
    ProductService productservice;

    @GetMapping("/listAll")
    @Operation(summary = "Obtener listado de todos los Productos")
    public ResponseEntity<List<ProductDTO>> listAll(){
        return ResponseEntity.ok(productservice.findAll());
    }

    @GetMapping("/listById")
    @Operation(summary = "Obtener un Producto por Id")
    public ResponseEntity<ProductDTO> listById(Long idproduct){
        return ResponseEntity.ok(productservice.findById(idproduct));
    }

    @GetMapping("/findByCodeLike")
    @Operation(summary = "Obtener listado de Productos filtrados por Código")
    public ResponseEntity<List<ProductDTO>> findByCodeLike(String code){
        return ResponseEntity.ok(productservice.findByCodeLike(code));
    }

    @GetMapping("/findByNameLike")
    @Operation(summary = "Obtener listado de Productos filtrados por Nombre")
    public ResponseEntity<List<ProductDTO>> findByNameLike(String name){
        return ResponseEntity.ok(productservice.findByNameLike(name));
    }

    @GetMapping("/findByCategoryId")
    @Operation(summary = "Obtener listado de Productos filtrados por Id de Categoría")
    public ResponseEntity<List<ProductDTO>> findByCategoryId(Long idcategory){
        return ResponseEntity.ok(productservice.findByCategoryId(idcategory));
    }

    @GetMapping("/findByCategoryName")
    @Operation(summary = "Obtener listado de Productos filtrados por Nombre de Categoría")
    public ResponseEntity<List<ProductDTO>> findByCategoryName(String categoryname){
        return ResponseEntity.ok(productservice.findByCategoryName(categoryname));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo Producto")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productservice.create(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Producto")
    public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        return productservice.update(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un Producto por Id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
