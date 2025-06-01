package com.erp.inventariapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.CustomerDTO;
import com.erp.inventariapp.Entities.Person;
import com.erp.inventariapp.Entities.Customer;
import com.erp.inventariapp.Exceptions.ResourceDeleteException;
import com.erp.inventariapp.Exceptions.ResourceNotFoundException;
import com.erp.inventariapp.Repositories.PersonRepository;
import com.erp.inventariapp.Repositories.CustomerRepository;
import com.erp.inventariapp.ServicesInterfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PersonRepository personrepository;

    @Autowired
    PersonService personservice;

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = (List<Customer>)customerRepository.findAll();
        List<CustomerDTO> customersDTO = new ArrayList<>();
        customers.forEach(s -> customersDTO.add(this.convertToDTO(s)));
        return(customersDTO);
    }

    @Override
    public CustomerDTO findById(Long idcustomer) {
        Customer s = customerRepository.findById(idcustomer).get();
        if(s.equals(null)){
            throw new ResourceNotFoundException("El Id: "+idcustomer+" de Customer NO Existe!");
        }
        return convertToDTO(s);
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer s = new Customer();
        s = convertToCustomer(s, dto);
        return convertToDTO(customerRepository.save(s));
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer s = new Customer();

        if(!customerRepository.existsById(id)){
            throw new ResourceNotFoundException("El Id: "+id+" de Customer NO existe!");
        }

        s = customerRepository.findById(id).get();
        s = convertToCustomer(s, dto);
        
        return convertToDTO(customerRepository.save(s));
    }

    @Override
    public void delete(Long idcustomer) {
        try {
            if (customerRepository.existsById(idcustomer)) {
                customerRepository.deleteById(idcustomer);
            } else {
                throw new ResourceNotFoundException("El Customer con ID " + idcustomer + " NO existe.");
            }
        } catch (Exception e) {
            throw new ResourceDeleteException("Error al eliminar el Customer.", e);
        }        
    }

    private CustomerDTO convertToDTO(Customer s){
        CustomerDTO dto = new CustomerDTO();
        dto.setIdcustomer(s.getIdcustomer());
        dto.setState(s.getState());
        dto.setPerson(personservice.findById(s.getPerson().getIdperson()));

        return dto;
    }

    private Customer convertToCustomer(Customer c, CustomerDTO dto){
        c.setIdcustomer(dto.getIdcustomer());
        c.setState(dto.getState());
        
        System.out.println("****--IDPERSON = "+dto.getPerson().getIdperson()+" --****");
        Optional<Person> optionalperson = personrepository.findById(dto.getPerson().getIdperson());
        if(optionalperson.isPresent()){
            c.setPerson(optionalperson.get());
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
            
            c.setPerson(person);             
        }
        System.out.println("****-- END convertToCustomer --****");        

        return c;
    }    
}
