package com.example.orderservice.entity;

import com.example.orderservice.dto.OrderDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
    private int quantity;
    private int priceAtOrder;
    private LocalDateTime orderDate;

    public Order() {}

    public Order(User user, Menu menu, int quantity, int priceAtOrder, LocalDateTime orderDate) {
        this.user = user;
        this.menu = menu;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPriceAtOrder() {
        return priceAtOrder;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
