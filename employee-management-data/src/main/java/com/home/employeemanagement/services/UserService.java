package com.home.employeemanagement.services;

import com.home.employeemanagement.model.UserAccount;
import com.home.employeemanagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CrudService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public List<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }

    public UserAccount getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(RuntimeException::new);
    }


    public UserAccount saveUser(UserAccount userAccount) {
        return userRepository.save(userAccount);
    }


}
