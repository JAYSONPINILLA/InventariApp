package com.erp.inventariapp.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.MeasurementDTO;
import com.erp.inventariapp.Entities.Measurement;
import com.erp.inventariapp.Repositories.MeasurementRepository;
import com.erp.inventariapp.ServicesInterfaces.IMeasurementService;

@Service
public class MeasurementService implements IMeasurementService {
    @Autowired
    MeasurementRepository measurementrepository;
    
    @Override
    public List<MeasurementDTO> findAll() {
        List<Measurement> measurements = (List<Measurement>) measurementrepository.findAll();
        List<MeasurementDTO> measurementsDTO = new ArrayList<>();
        measurements.forEach(m -> measurementsDTO.add(this.convertToDTO(m)));
        return measurementsDTO;
    }

    @Override
    public MeasurementDTO findById(Long idMeasurement) {
        Measurement m = measurementrepository.findById(idMeasurement).get();
        return (this.convertToDTO(m)); 
    }

    @Override
    public MeasurementDTO create(MeasurementDTO dto) {
        Measurement m = new Measurement();
        return saveOrUpdate(m, dto);
    }

    @Override
    public MeasurementDTO update(Long id, MeasurementDTO dto) {
        Measurement m = measurementrepository.findById(id).get();
        return saveOrUpdate(m, dto);
    }

    @Override
    public void delete(Long idMeasurement) {
        //if (!measurementrepository.existsById(idMeasurement))
        //    throw ResourceNotFoundException("Unidad de medida no encontrada");
        measurementrepository.deleteById(idMeasurement);
    }

    private MeasurementDTO saveOrUpdate(Measurement m, MeasurementDTO dto) {
        m.setName(dto.getName());
        m.setState(dto.getState());
        return convertToDTO(measurementrepository.save(m));
    }

    private MeasurementDTO convertToDTO(Measurement m) {
        MeasurementDTO dto = new MeasurementDTO();
        dto.setIdmeasurement(m.getIdmeasurement());
        dto.setName(m.getName());
        dto.setState(m.getState());
        return dto;
    }    

}
