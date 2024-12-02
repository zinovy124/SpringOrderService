package com.example.orderservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    private String email;
    private String name;
    private String password;
    private String profileImagePath;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User() {}

    public User(String email, String name, String password, String profileImagePath) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileImagePath = profileImagePath;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }
}
