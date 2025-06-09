package com.bankexample.cardmanagementsystem.service;

import com.bankexample.cardmanagementsystem.model.dto.JwtAuthenticationResponse;
import com.bankexample.cardmanagementsystem.model.dto.UserResponseWithCredentials;
import com.bankexample.cardmanagementsystem.model.dto.request.UserCreateRequest;
import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsImpl customUserDetails;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, CustomUserDetailsImpl customUserDetails, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.customUserDetails = customUserDetails;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    //Регистрация пользователя
    public JwtAuthenticationResponse register(@Valid UserCreateRequest userCreateRequest) {
        User user = userMapper.toCreateRequest(userCreateRequest);
        user.setPassword(passwordEncoder.encode(userCreateRequest.password()));

        userService.create(user); 

        UserDetails userDetails = customUserDetails.loadUserByUsername(user.getUsername());
        String token = jwtService.generateToken(userDetails);

        return new JwtAuthenticationResponse(token);
    }

    // Авторизация пользователя
    public JwtAuthenticationResponse login(UserResponseWithCredentials userResponseWithCredentials) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userResponseWithCredentials.username(),
                userResponseWithCredentials.password()));

        UserDetails user = customUserDetails.loadUserByUsername(userResponseWithCredentials.username());
        String token = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(token);
    }
}
