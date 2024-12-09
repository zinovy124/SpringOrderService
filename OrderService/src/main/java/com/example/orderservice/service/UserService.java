package com.example.orderservice.service;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.User;
import com.example.orderservice.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String UPLOAD_DIR = "uploads/";

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    public UserDto addUser(UserDto userDto) {
//        User user = UserDto.toEntity(userDto);
//        userRepository.save(user);
//        return UserDto.fromEntity(user);
//    }

    public UserDto registerUser(String email, String username, String rawPassWord, MultipartFile profileImage) throws IOException {
//        System.out.println("method started" + rawPassWord);
        if (rawPassWord == null || rawPassWord.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(email + " aleady exists");
        }
        String encryptedPassword = passwordEncoder.encode(rawPassWord);
//        System.out.println(encryptedPassword);
//        System.out.println("image");
        String profileImagePath = this.saveProfileImage(profileImage);
//        System.out.println("saved");
        User user = new User(email, username, encryptedPassword, profileImagePath);
        userRepository.save(user);
        return UserDto.fromEntity(user);
    }

    public String saveProfileImage(MultipartFile profileImage) throws IOException {
        String profileImagePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = profileImage.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, profileImage.getBytes());
            profileImagePath = filePath.toString();
        }
        return profileImagePath;
    }

    public boolean login(String email, String rawPassword, HttpSession session) {
        try {
            User user = this.findByEmail(email);
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                System.out.println("Login successful by user: " + user.getEmail());
                session.setAttribute("user", user.getEmail());
                return true;
            } else {
                System.out.println("Login failed");
                return false;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User loginCheckBySession(HttpSession session) {
        try {
            String userEmail = (String)session.getAttribute("user");
            return this.findByEmail(userEmail);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}
