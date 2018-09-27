package com.autentia.tnt.api.rest.controller;

import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    public User user() {
        return userService.getUserByLogin();
    }
}
