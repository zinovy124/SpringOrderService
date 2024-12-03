package com.example.orderservice.controller;

import com.example.orderservice.dto.RegisterDto;
import com.example.orderservice.dto.UserDto;
import com.example.orderservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@ModelAttribute RegisterDto registerDto) throws IOException {
        try {
//            System.out.println("before" + registerDto.email());
            UserDto addedUser = userService.registerUser(
                    registerDto.email(),
                    registerDto.name(),
                    registerDto.rawPassword(),
                    registerDto.profileImage()
            );
//            System.out.println("after: " + addedUser.email());
//            System.out.println("after: " + addedUser.name());

            return ResponseEntity.ok("User" + addedUser.email() + " successfully added");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body("Registration Failed " + e.getMessage());
        }
    }

//    @PostMapping
//    public RedirectView registerUser(@RequestBody UserDto userDto) {
//        UserDto addedUser = userService.addUser(userDto);
//        return new RedirectView("/menu");
//    }

}
