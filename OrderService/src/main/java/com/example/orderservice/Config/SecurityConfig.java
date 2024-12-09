package com.example.orderservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll()
//                        .requestMatchers("/api/login", "/api/register", "/api/user", "/api/order").permitAll()
//                        .requestMatchers("/api/order").authenticated() 일단 SpringSecurity 에선 비활성화 후 비즈니스 로직으로 잡는다.
                )
                .sessionManagement(session -> session
                        .sessionFixation().none()
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );

        return http.build();
    }
}
