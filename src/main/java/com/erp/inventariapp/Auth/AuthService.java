package com.erp.inventariapp.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.inventariapp.DTOs.PersonDTO;
import com.erp.inventariapp.Entities.Person;
import com.erp.inventariapp.Services.JwttService;
import com.erp.inventariapp.Entities.UserApp;
import com.erp.inventariapp.Enums.RoleUserEnum;
import com.erp.inventariapp.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwttService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
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
