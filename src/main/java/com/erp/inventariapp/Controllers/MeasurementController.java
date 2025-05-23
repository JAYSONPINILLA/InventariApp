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

import com.erp.inventariapp.DTOs.MeasurementDTO;
import com.erp.inventariapp.Services.MeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/measurement")
@Tag(name="Measurements")
public class MeasurementController {
    @Autowired
    MeasurementService measurementservice;

    @GetMapping("/listAll")
    @Operation(summary = "Obtener listado de todas las Unidades de medida")
    public ResponseEntity<List<MeasurementDTO>> listAll(){
        return ResponseEntity.ok(measurementservice.findAll());
    }

    @GetMapping("/listById")
    @Operation(summary = "Obtener una Unidad de medida por Id")
    public ResponseEntity<MeasurementDTO> listById(Long idmeasurement){
        return ResponseEntity.ok(measurementservice.findById(idmeasurement));
    }

    @PostMapping
    @Operation(summary = "Crear nueva unidad de medida")
    public ResponseEntity<MeasurementDTO> create(@Valid @RequestBody MeasurementDTO measurementDTO) {
        return new ResponseEntity<>(measurementservice.create(measurementDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar unidad de medida")
    public MeasurementDTO update(@PathVariable Long id, @Valid @RequestBody MeasurementDTO measurementDTO) {
        return measurementservice.update(id, measurementDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar una Unidad de medida por Id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        measurementservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
