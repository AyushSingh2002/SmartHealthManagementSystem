package com.incture.SmartHealthManagement.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/Role/add-role", 
                		"/api/v1/User/add-user",
                		"/api/v1/User/get-all-users",
                		"/api/v1/Doctor/add-doctor",
                		"/api/v1/Doctor/get-all-doctors",
                		"/api/v1/Patient/add-patient",
                		"/api/v1/Patient/get-all-patients",
                		"/api/v1/Appointment/get-all-appointments",
                		"/api/v1/Appointment/add-appointment")
                .permitAll()
                .anyRequest().authenticated() 
            )
            .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
