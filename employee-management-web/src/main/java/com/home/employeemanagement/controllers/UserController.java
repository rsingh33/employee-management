package com.home.employeemanagement.controllers;


import com.home.employeemanagement.model.UserAccount;
import com.home.employeemanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder bCryptEncoder;

    public UserController(UserService userService, PasswordEncoder bCryptEncoder) {
        this.userService = userService;
        this.bCryptEncoder = bCryptEncoder;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccount> getAllUserAccounts() {
        List<UserAccount> userAccounts = userService.getAllUsers();

        userAccounts.forEach(userAccount -> userAccount.add(linkTo(UserController.class)
                .slash(userAccount.getUserId())
                .withSelfRel()));

        return userAccounts;
    }

    @GetMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccount getUserById(@PathVariable String userID) {
        UserAccount userAccount = userService.getUserById(Long.valueOf(userID));
        userAccount.add(linkTo(UserController.class)
                .slash(userAccount.getUserId())
                .withSelfRel());
        return userAccount;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccount saveUser(@RequestBody UserAccount userAccount) {
        log.debug("###### \n" +userAccount);
        userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
        return userService.saveUser(userAccount);
    }


}
