package com.example.orderservice.controller;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/register")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public RedirectView registerUser(@ModelAttribute UserDto userDto) {
        UserDto addedUser = userService.addUser(userDto);
        return new RedirectView("/menu");
    }

//    @PostMapping
//    public RedirectView registerUser(@RequestBody UserDto userDto) {
//        UserDto addedUser = userService.addUser(userDto);
//        return new RedirectView("/menu");
//    }

}
