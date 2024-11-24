package com.example.orderservice.dto;

import com.example.orderservice.entity.User;

public record UserDto(int id, String name) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getName()
        );
    }

    public static User toEntity(UserDto userDto) {
        return new User(
                userDto.name
        );
    }
}
