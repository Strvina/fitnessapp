package com.strvina.fitnessapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.strvina.fitnessapp.model.User;
import com.strvina.fitnessapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repo) {
        this.userRepository = repo;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean userExistsByUsername(String name) {
        return userRepository.findByName(name).isPresent();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
