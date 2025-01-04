package com.hmsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@SuppressWarnings("removal")
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JWTFilter  jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilterBefore(jwtFilter, AuthenticationFilter.class);
       // http.authorizeHttpRequests().requestMatchers("/api/auth/sign-up","/api/auth/login","/api/auth/property/sign-up")
              //  .permitAll()
             //  .requestMatchers("/api/v1/property/addProperty")
             //   .hasRole("OWNER")
             //   .requestMatchers("/api/v1/property/deleteProperty")
             //   .hasAnyRole("OWNER","ADMIN")
               // .requestMatchers("/api/auth/blog/sign-up")
               // .hasRole("ADMIN")
               // .anyRequest().authenticated();  // Only authenticated users can access other requests
       // http.addFilterBefore(jwtFilter, AuthenticationFilter.class);
       // http.authorizeHttpRequests().anyRequest().permitAll();  // Allow all requests
        return http.build();
    }
}





