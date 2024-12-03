package com.example.orderservice.dto;

import com.example.orderservice.entity.User;
import org.springframework.web.multipart.MultipartFile;

public record RegisterDto(String email, String name, String rawPassword, MultipartFile profileImage) {
}
