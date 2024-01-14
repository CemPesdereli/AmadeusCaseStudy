package com.cem.flight.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(admin);


    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/airports/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/airports/**").hasRole("ADMIN")
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/airports/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/airports/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/flights/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/flights/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/flights/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/flights/**").hasRole("ADMIN")

        );

        // use HTTP basic auth
        http.httpBasic(Customizer.withDefaults());

        // disable cross site request forgery
        http.csrf(csrf -> csrf.disable());
        return http.build();

    }


}
