package com.erp.inventariapp.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    String username;
    //String role; 

/*     public AuthResponse(String token, String username, String role){
        this.token = token;
        this.username = username;
        this.role = role;
    } */
}
