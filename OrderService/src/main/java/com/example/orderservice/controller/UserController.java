package com.example.orderservice.controller;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/register")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String view() {
        return "register";
    }

    @PostMapping
    public RedirectView registerUser(@ModelAttribute UserDto userDto) {
        System.out.println(userDto.id());
        System.out.println(userDto.name());
        UserDto addedUser = userService.addUser(userDto);
        return new RedirectView("/menu");
    }

//    @PostMapping
//    public RedirectView registerUser(@RequestBody UserDto userDto) {
//        UserDto addedUser = userService.addUser(userDto);
//        return new RedirectView("/menu");
//    }

}
