package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Menu;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.User;
import com.example.orderservice.repository.MenuRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private MenuRepository menuRepository;

    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository,
            MenuRepository menuRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.userId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Menu menu = menuRepository.findById(orderDto.menuId()).orElseThrow(() -> new RuntimeException("Menu Not Found"));

        Order order = OrderDto.toEntity(orderDto, user, menu);
        orderRepository.save(order);
        return OrderDto.fromEntity(order);
    }

    public void deleteOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.userId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Order order = orderRepository.findById(orderDto.id()).orElseThrow(() -> new RuntimeException("Order Not Found"));
        orderRepository.delete(order);
    }

    public OrderDto changeOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.userId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Menu menu = menuRepository.findById(orderDto.menuId()).orElseThrow(() -> new RuntimeException("Menu not found"));

        Order newOrder = OrderDto.toEntity(orderDto, user, menu);
        orderRepository.save(newOrder);
        return OrderDto.fromEntity(newOrder);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
        System.out.println("Order loaded: " + order);
        return OrderDto.fromEntity(order);
    }
}
