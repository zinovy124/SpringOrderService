package com.example.orderservice.dto;

import com.example.orderservice.entity.User;

public record UserDto(String email, String name, String profileImagePath) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getEmail(),
                user.getName(),
                user.getProfileImagePath()
        );
    }

    public static User toEntity(UserDto userDto) {
        return new User(
                userDto.name,
                userDto.email,
                null,
                userDto.profileImagePath
        );
    }
}
