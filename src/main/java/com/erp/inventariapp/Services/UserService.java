package com.erp.inventariapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.UserDTO;
import com.erp.inventariapp.Entities.Person;
import com.erp.inventariapp.Entities.UserApp;
import com.erp.inventariapp.Exceptions.ResourceNotFoundException;
import com.erp.inventariapp.Repositories.UserRepository;
import com.erp.inventariapp.Repositories.PersonRepository;
import com.erp.inventariapp.ServicesInterfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userrepository;
   
    @Autowired
    PersonRepository personrepository;

    @Autowired
    PersonService personservice;

    @Override
    public List<UserDTO> findAll() {
        List<UserApp> users = (List<UserApp>) userrepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(u -> usersDTO.add(this.convertToDTO(u)));
        return usersDTO;
    }

    @Override
    public UserDTO findById(Long iduser) {
        UserApp u = userrepository.findById(iduser).get();
        return (this.convertToDTO(u)); 
    }

    @Override
    public UserDTO create(UserDTO dto) {
        UserApp u = new UserApp();
        return saveOrUpdate(u, dto);
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        UserApp u = userrepository.findById(id).get();
        return saveOrUpdate(u, dto);
    }

    @Override
    public void delete(Long iduser) {
        if (!userrepository.existsById(iduser))
            throw new ResourceNotFoundException("Usuario no encontrado");
        userrepository.deleteById(iduser);
    }

    private UserDTO saveOrUpdate(UserApp u, UserDTO dto) {
        //Person person = modelMapper.map(dto.getPerson(), Person.class);

        u.setUsername(dto.getUsername());

        //u.setPassword(dto.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(dto.getPassword());
        u.setPassword(hashedPassword); // Guarda el hash, no el texto plano
        //Para verificar contraseñas al Iniciar Sesión
        //boolean matches = encoder.matches(enteredPassword, userFromDB.getPassword());
        
        u.setRoleUser(dto.getRoleUser());
        u.setState(dto.getState());
        System.out.println("****--IDPERSON = "+dto.getPerson().getIdperson()+" --****");
        Optional<Person> optionalperson = personrepository.findById(dto.getPerson().getIdperson());
        if(optionalperson.isPresent()){
            u.setPerson(optionalperson.get());
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
            
            u.setPerson(person);             
        }
        
        System.out.println("****-- END SAVEORCREATE --****");
        
        return convertToDTO(userrepository.save(u));
    }

    private UserDTO convertToDTO(UserApp u) {
        UserDTO dto = new UserDTO();
        dto.setIduser(u.getIduser());
        dto.setUsername(u.getUsername());
        dto.setPassword(u.getPassword());
        dto.setRoleUser(u.getRoleUser());
        dto.setState(u.getState());
        
        if(u.getPerson()!=null){
            dto.setPerson(personservice.findById(u.getPerson().getIdperson()));
        }else{
            dto.setPerson(null);
        }

        return dto;
    }    
}
