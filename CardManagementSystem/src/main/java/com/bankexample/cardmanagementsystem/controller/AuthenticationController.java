package com.bankexample.cardmanagementsystem.controller;

import com.bankexample.cardmanagementsystem.model.dto.JwtAuthenticationResponse;
import com.bankexample.cardmanagementsystem.model.dto.request.UserCreateRequest;
import com.bankexample.cardmanagementsystem.model.dto.UserResponseWithCredentials;
import com.bankexample.cardmanagementsystem.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@Valid @RequestBody UserResponseWithCredentials userResponseWithCredentials) {

        System.out.println("Controller login called: " + userResponseWithCredentials);
        return authenticationService.login(userResponseWithCredentials);
    }

    @PostMapping("/register")
    public JwtAuthenticationResponse register(@Valid @RequestBody UserCreateRequest userCreateRequest) {

        JwtAuthenticationResponse response = authenticationService.register(userCreateRequest);

        System.out.println(">>> Регистрация завершена. JWT: " + response.token());
        return response;
    }

}
