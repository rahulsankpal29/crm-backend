package com.crm.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for API-based usage
            .authorizeHttpRequests()
                .requestMatchers("/api/customers/**").authenticated() // Secure this route
                .anyRequest().permitAll()
            .and()
            .httpBasic(); // Use HTTP Basic authentication

        return http.build();
    }
}
