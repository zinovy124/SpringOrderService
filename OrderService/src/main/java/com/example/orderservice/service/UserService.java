package com.example.orderservice.service;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.User;
import com.example.orderservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addUser(UserDto userDto) {
        User user = UserDto.toEntity(userDto);
        userRepository.save(user);
        return UserDto.fromEntity(user);
    }

    public void registerUser(String email, String username, String rawPassWord, String profileImagePath) {
        String encryptedPassword = passwordEncoder.encode(rawPassWord);
        User user = new User(email, username, encryptedPassword, profileImagePath);
        userRepository.save(user);
    }
}
