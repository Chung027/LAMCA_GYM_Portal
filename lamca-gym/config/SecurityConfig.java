package com.example.lamcagym.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())              // vi använder egna REST/fetch-anrop
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()              // släpp igenom allt – vi sköter inloggning själva
            )
            .formLogin(form -> form.disable())         // stäng av Spring Securitys egen /login-sida
            .httpBasic(basic -> basic.disable());       // stäng av popup-inloggning
        return http;
    }
}
