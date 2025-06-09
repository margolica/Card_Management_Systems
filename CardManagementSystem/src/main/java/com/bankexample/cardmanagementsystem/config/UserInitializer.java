package com.bankexample.cardmanagementsystem.config;

import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.enums.EGender;
import com.bankexample.cardmanagementsystem.model.enums.ERole;
import com.bankexample.cardmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        User user = userRepository.findByUsername("user")
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode("user"));

        User admin = userRepository.findByUsername("user")
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        admin.setPassword(passwordEncoder.encode("admin"));

//        if (!userRepository.existsByUsername("user")) {
//            User user = User.builder().
//                    username("user").
//                    password(passwordEncoder.encode("user")).
//                    email("user@mail.ru").
//                    firstName("user").
//                    lastName("user").
//                    middleName("user").
//                    birthday(LocalDate.of(2000, 1, 1).atStartOfDay()).
//                    gender(EGender.MALE).
//                    registrationDate(LocalDateTime.now()).
//                    role(ERole.USER)
//                    .build();
//
//            userRepository.save(user);
//        }
//
//        if (!userRepository.existsByUsername("admin")) {
//            User user = User.builder().
//                    username("admin").
//                    password(passwordEncoder.encode("admin")).
//                    email("admin@mail.ru").
//                    firstName("admin").
//                    lastName("admin").
//                    middleName("admin").
//                    birthday(LocalDate.of(2000, 1, 1).atStartOfDay()).
//                    gender(EGender.MALE).
//                    registrationDate(LocalDateTime.now()).
//                    role(ERole.ADMIN)
//                    .build();
//
//            userRepository.save(user);
//        }
    }
}
