package com.erp.inventariapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.SellerDTO;
import com.erp.inventariapp.Entities.Person;
import com.erp.inventariapp.Entities.Seller;
import com.erp.inventariapp.Exceptions.ResourceDeleteException;
import com.erp.inventariapp.Exceptions.ResourceNotFoundException;
import com.erp.inventariapp.Repositories.PersonRepository;
import com.erp.inventariapp.Repositories.SellerRepository;
import com.erp.inventariapp.ServicesInterfaces.ISellerService;

@Service
public class SellerService implements ISellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PersonRepository personrepository;

    @Autowired
    PersonService personservice;

    @Override
    public List<SellerDTO> findAll() {
        List<Seller> sellers = (List<Seller>)sellerRepository.findAll();
        List<SellerDTO> sellersDTO = new ArrayList<>();
        sellers.forEach(s -> sellersDTO.add(this.convertToDTO(s)));
        return(sellersDTO);
    }

    @Override
    public SellerDTO findById(Long idseller) {
        Seller s = sellerRepository.findById(idseller).get();
        if(s.equals(null)){
            throw new ResourceNotFoundException("El Id: "+idseller+" de Seller NO Existe!");
        }
        return convertToDTO(s);
    }

    @Override
    public SellerDTO create(SellerDTO dto) {
        Seller s = new Seller();
        s = convertToSeller(s, dto);
        return convertToDTO(sellerRepository.save(s));
    }

    @Override
    public SellerDTO update(Long id, SellerDTO dto) {
        Seller s = new Seller();

        if(!sellerRepository.existsById(id)){
            throw new ResourceNotFoundException("El Id: "+id+" de Seller NO existe!");
        }

        s = sellerRepository.findById(id).get();
        s = convertToSeller(s, dto);
        
        return convertToDTO(sellerRepository.save(s));
    }

    @Override
    public void delete(Long idseller) {
        try {
            if (sellerRepository.existsById(idseller)) {
                sellerRepository.deleteById(idseller);
            } else {
                throw new ResourceNotFoundException("El Seller con ID " + idseller + " NO existe.");
            }
        } catch (Exception e) {
            throw new ResourceDeleteException("Error al eliminar el Seller.", e);
        }        
    }

    private SellerDTO convertToDTO(Seller s){
        SellerDTO dto = new SellerDTO();
        dto.setIdseller(s.getIdseller());
        dto.setState(s.getState());
        dto.setPerson(personservice.findById(s.getPerson().getIdperson()));

        return dto;
    }

    private Seller convertToSeller(Seller s, SellerDTO dto){
        s.setIdseller(dto.getIdseller());
        s.setState(dto.getState());
        
        System.out.println("****--IDPERSON = "+dto.getPerson().getIdperson()+" --****");
        Optional<Person> optionalperson = personrepository.findById(dto.getPerson().getIdperson());
        if(optionalperson.isPresent()){
            s.setPerson(optionalperson.get());
        }else{
            System.out.println("****-- NOT FOUND PERSON --****");
            // Crear nueva persona con los datos del DTO
            Person person = Person.builder()
            .typeId(dto.getPerson().getTypeId())
            .identification(dto.getPerson().getIdentification())
            .name(dto.getPerson().getName())
            .adress(dto.getPerson().getAdress())
            .email(dto.getPerson().getEmail())
            .phone(dto.getPerson().getPhone())
            .birthdate(dto.getPerson().getBirthdate())
            .genre(dto.getPerson().getGenre())
            .build();
            
            s.setPerson(person);            
        }
        System.out.println("****-- END convertToSeller --****");        

        return s;
    }    
}
