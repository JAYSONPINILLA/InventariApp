package com.erp.inventariapp.Config;

import com.erp.inventariapp.JWT.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Habilita CORS
                .csrf(csrf -> csrf.disable())    // Desactiva CSRF si estás trabajando con APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", 
                                        "/api-docs/**",
                                        "/v3/api-docs/**", 
                                        "/swagger-ui/**", 
                                        "/swagger-ui.html",
                                        "/v3/api-docs.yaml").permitAll()
                        .anyRequest().permitAll()
                        //.anyRequest().authenticated()
                    )
                //.formLogin(Customizer.withDefaults());
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}