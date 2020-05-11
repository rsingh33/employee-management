package com.home.employeemanagement.controllers;


import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.UserAccount;
import com.home.employeemanagement.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        return userService.saveUser(userAccount);
    }


}
