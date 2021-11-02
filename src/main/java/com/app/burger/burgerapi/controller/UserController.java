package com.app.burger.burgerapi.controller;

import com.app.burger.burgerapi.repo.models.UserDto;
import com.app.burger.burgerapi.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto dto) {
        userService.registerUser(dto);
    }
}
