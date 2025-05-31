package com.erp.inventariapp.Auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.PersonDTO;
import com.erp.inventariapp.Entities.Person;
import com.erp.inventariapp.Entities.UserApp;
import com.erp.inventariapp.Enums.RoleUserEnum;
import com.erp.inventariapp.Repositories.UserRepository;
import com.erp.inventariapp.Services.JwttService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwttService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    /*
    public AuthService(JwttService jwtService, UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder){
        this.jwtService=jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    */

    public AuthResponse login(LoginRequest request){
        System.out.println("***** Ingresando a AuthService.login");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println("***** Paso 1");
        List<UserApp> users = (List<UserApp>) userRepository.findAll();
        System.out.println("***** Paso 2");
        if(users.size()==0){
            System.out.println("***** NO HAY Usuarios");
        }else{
            System.out.println("***** SI HAY Usuarios");
        }
        System.out.println("***** Paso 2.1");
        UserApp user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        System.out.println("***** Paso 3");

        String token = jwtService.getToken(user);
        System.out.println("***** Paso 4");
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        Person p = new Person();
        p = convertToPerson(p, request.getPerson());

        UserApp user = UserApp.builder()
                    .username(request.username)
                    .password(passwordEncoder.encode(request.password))
                    .roleUser(RoleUserEnum.CUSTOMER)
                    .state(request.state)
                    .person(p)
                    .build();
        
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    private Person convertToPerson(Person p, PersonDTO dto) {
        p.setIdperson(dto.getIdperson());
        p.setTypeId(dto.getTypeId());
        p.setIdentification(dto.getIdentification());
        p.setName(dto.getName());
        p.setAdress(dto.getAdress());
        p.setBirthdate(dto.getBirthdate());
        p.setPhone(dto.getPhone());
        p.setEmail(dto.getEmail());
        p.setGenre(dto.getGenre());        
        
        return p;
    }

}
