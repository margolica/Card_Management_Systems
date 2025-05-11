package com.bankexample.cardmanagementsystem.service;


import com.bankexample.cardmanagementsystem.model.User;
import com.bankexample.cardmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
//        user.setEmail(updatedUser.getEmail());
//        user.setFirstName(updatedUser.getFirstName());
        // и так далее
        // TODO Сделать builder
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User registration(User user) {
        return userRepository.save(user);
    }
}
