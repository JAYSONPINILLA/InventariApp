package com.erp.inventariapp.ServicesInterfaces;

import java.util.List;

import com.erp.inventariapp.DTOs.MeasurementDTO;

public interface IMeasurementService {
    public List<MeasurementDTO> findAll();
    public MeasurementDTO findById(Long idmeasurement);
    public MeasurementDTO create(MeasurementDTO measurement);
    public MeasurementDTO update(Long idmeasurement, MeasurementDTO measurement);
    public void delete(Long idmeasurement);
}
