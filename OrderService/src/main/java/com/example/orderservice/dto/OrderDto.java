package com.example.orderservice.dto;

import com.example.orderservice.entity.Menu;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record OrderDto(
        Integer id,
        Integer userId,
        Integer menuId,
        int quantity,
        int priceAtOrder,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime orderDate
) {
    public static OrderDto fromEntity(Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getMenu().getId(),
                order.getQuantity(),
                order.getPriceAtOrder(),
                order.getOrderDate()
        );
    }

    public static Order toEntity(OrderDto dto, User user, Menu menu) {
        return new Order(
                user,
                menu,
                dto.quantity(),
                dto.priceAtOrder(),
                dto.orderDate()
        );
    }
}
