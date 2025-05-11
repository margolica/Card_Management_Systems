package com.bankexample.cardmanagementsystem.config;

import com.bankexample.cardmanagementsystem.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        return http.csrf(customizer -> customizer.disable()) // Отключение CSRF
            .authorizeHttpRequests(request -> request.anyRequest().authenticated()) // Обязательная аунтефикация для всех
            .formLogin(Customizer.withDefaults()) // TODO указать сою форму
            .httpBasic(Customizer.withDefaults()) // Для авторизации через Postmen по API без формы
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Отключение HttpSession для реализации JWT(токен)
            .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User
//                .withUserDetails() // Шифрукм парол TODO Старый метод, переделать по возможности
//
//        return new InMemoryUserDetailsManager();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(myUserDetailsServiceImpl); // Собственный сервис по работе с данными аунтефикации пользователя
        return provider;
    }
}
