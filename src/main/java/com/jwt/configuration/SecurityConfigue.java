package com.jwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfigue {

    private JWTFilter jwtFilter;

    public SecurityConfigue(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
  http.addFilterBefore(jwtFilter, AuthenticationFilter.class);

  http.authorizeHttpRequests()
          .requestMatchers("/api/v1/user/login","/api/v1/user/signup","/api/v1/user/signup-property-owner")
          .permitAll()
          .requestMatchers("/api/v1/country/addCountry").hasAnyRole("ADMIN","OWNER")
          .anyRequest().authenticated();

        return http.build();
    }
}
