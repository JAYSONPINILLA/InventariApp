package com.erp.inventariapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.UserDTO;
import com.erp.inventariapp.Entities.Person;
import com.erp.inventariapp.Entities.User;
import com.erp.inventariapp.Repositories.UserRepository;
import com.erp.inventariapp.Repositories.PersonRepository;
import com.erp.inventariapp.ServicesInterfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userrepository;
   
    @Autowired
    PersonRepository personrepository;

    @Override
    public List<UserDTO> findAll() {
        List<User> users = (List<User>) userrepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(u -> usersDTO.add(this.convertToDTO(u)));
        return usersDTO;
    }

    @Override
    public UserDTO findById(Long iduser) {
        User u = userrepository.findById(iduser).get();
        return (this.convertToDTO(u)); 
    }

    @Override
    public UserDTO create(UserDTO dto) {
        User u = new User();
        return saveOrUpdate(u, dto);
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User u = userrepository.findById(id).get();
        return saveOrUpdate(u, dto);
    }

    @Override
    public void delete(Long iduser) {
        //if (!userrepository.existsById(iduser))
        //    throw ResourceNotFoundException("Categoría no encontrada");
        userrepository.deleteById(iduser);
    }

    private UserDTO saveOrUpdate(User u, UserDTO dto) {
        u.setUsername(dto.getUsername());

        //u.setPassword(dto.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(dto.getPassword());
        u.setPassword(hashedPassword); // Guarda el hash, no el texto plano
        //Para verificar contraseñas al Iniciar Sesión
        //boolean matches = encoder.matches(enteredPassword, userFromDB.getPassword());
        
        u.setRole(dto.getRole());
        u.setState(dto.getState());
        System.out.println("****--IDPERSON = "+dto.getIdperson()+" --****");
        Optional<Person> optionalperson = personrepository.findById(dto.getIdperson());
        if(optionalperson.isPresent()){
            u.setPerson(optionalperson.get());
        }else{
            System.out.println("****-- NOT FOUND PERSON --****");
        }
        System.out.println("****-- END SAVEORCREATE --****");
        return convertToDTO(userrepository.save(u));
    }

    private UserDTO convertToDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.setIduser(u.getIduser());
        dto.setUsername(u.getUsername());
        dto.setPassword(u.getPassword());
        dto.setRole(u.getRole());
        dto.setState(u.getState());
        System.out.println("****-- BEFORE EVALUATE PERSON IN CONVERTODTO --****");
        if(u.getPerson()!=null)
            dto.setIdperson(u.getPerson().getIdperson());
        System.out.println("****-- AFTER EVALUATE PERSON IN CONVERTODTO --****");
            return dto;
    }    
}
