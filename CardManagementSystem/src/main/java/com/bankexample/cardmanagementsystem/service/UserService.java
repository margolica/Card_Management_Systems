package com.bankexample.cardmanagementsystem.service;

import com.bankexample.cardmanagementsystem.model.dto.request.UserUpdateRequest;
import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.mapper.UserMapper;
import com.bankexample.cardmanagementsystem.repository.UserRepository;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    // Поиск пользователя
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    // Регистрация пользователя
    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("Пользователь с таким логином уже существует"); // TODO Обработать самостоятельно
        return userRepository.save(user);
    }
    public Page<User> getAllUsers(@ParameterObject Pageable pageable) {
         return userRepository.findAll(pageable);
    }

    public User update(UserUpdateRequest updateRequest) {
        User user = userRepository.findByUsername(updateRequest.username()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        userMapper.updateUserFromRequest(updateRequest, user);
        return userRepository.save(user);
    }

    public void delete(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        userRepository.delete(user);
    }
}
