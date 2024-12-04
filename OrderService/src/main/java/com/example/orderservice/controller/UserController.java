package com.example.orderservice.controller;

import com.example.orderservice.dto.RegisterDto;
import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.User;
import com.example.orderservice.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {
        try {
            boolean authenticated = userService.authenticate(email, password);
            if (authenticated) {
                session.setAttribute("user", email);
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred" + e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> loginCheck(HttpSession session) {
        System.out.println(session);
        String email = (String) session.getAttribute("user");
        System.out.println(email);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setCacheControl(CacheControl.noStore());
//        headers.set("Set-Cookie", "");
        System.out.println(session.getId() + " " + session.isNew());
        System.out.println(email);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDto userDto = UserDto.fromEntity(user);
        return ResponseEntity.ok(userDto);
    }

}
