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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.inventariapp.DTOs.PersonDTO;
import com.erp.inventariapp.Exceptions.ResourceNotFoundException;
import com.erp.inventariapp.Services.PersonService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/persons")
@Tag(name="Persons")
public class PersonController {
    @Autowired
    PersonService personService;
    
    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> dtos = personService.findAll();
        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{idperson}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long idperson) {
        PersonDTO dto = personService.findById(idperson);
        if (dto.equals(null)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
        return new ResponseEntity<>(dto, HttpStatus.OK); // 200 OK        
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO dto) {
        PersonDTO ret = personService.create(dto);
        return new ResponseEntity<>(ret, HttpStatus.CREATED);
    }

    @PutMapping("/{idperson}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO dto) {
        PersonDTO ret = personService.update(id, dto);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @DeleteMapping("/{idperson}")
    public ResponseEntity<String> delete(@RequestParam Long idperson){
        try {
            personService.delete(idperson);
            return new ResponseEntity<>("Persona eliminada correctamente", HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("No se encuentra la Persona con ID: "+idperson, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al intentar eliminar la persona con ID: "+idperson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
