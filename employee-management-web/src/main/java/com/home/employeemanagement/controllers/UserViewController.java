package com.home.employeemanagement.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.home.employeemanagement.model.UserAccount;
import com.home.employeemanagement.model.Views;
import com.home.employeemanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequestMapping("/users/views")
public class UserViewController {

    private final UserService userService;
    private final PasswordEncoder bCryptEncoder;

    public UserViewController(UserService userService, PasswordEncoder bCryptEncoder
    ) {
        this.userService = userService;
        this.bCryptEncoder = bCryptEncoder;
    }

/*

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccount> getAllUserAccounts() {
        List<UserAccount> userAccounts = userService.getAllUsers();

        userAccounts.forEach(userAccount -> userAccount.add(linkTo(UserViewController.class)
                .slash("views")
                .slash(userAccount.getUserId())
                .withSelfRel()));

        return userAccounts;
    }
*/

    @JsonView(Views.ExternalUser.class)
    @GetMapping("/external/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccount getExternalUserById(@PathVariable String userID) {
        UserAccount userAccount = userService.getUserById(Long.valueOf(userID));

        userAccount.add(linkTo(UserViewController.class)
                .slash("views")
                .slash("external")
                .slash(userAccount.getUserId())
                .withSelfRel());

        return userAccount;
    }

    @JsonView(Views.InternalUser.class)
    @GetMapping("/internal/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccount getInternalUserById(@PathVariable String userID) {
        UserAccount userAccount = userService.getUserById(Long.valueOf(userID));

        userAccount.add(linkTo(UserViewController.class)
                .slash("views")
                .slash("internal")
                .slash(userAccount.getUserId())
                .withSelfRel());
        return userAccount;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccount saveUser(@RequestBody UserAccount userAccount) {
        log.debug("###### \n" + userAccount);
        userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
        return userService.saveUser(userAccount);
    }


}
