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

    public OrderDto createOrder(OrderDto orderDto, String email) {
        User user = userRepository.findByEmail(orderDto.userEmail()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Menu menu = menuRepository.findById(orderDto.menuId()).orElseThrow(() -> new RuntimeException("Menu Not Found"));

        Order order = OrderDto.toEntity(orderDto, user, menu);
        orderRepository.save(order);
        return OrderDto.fromEntity(order);
    }

    /*public void deleteOrder(OrderDto orderDto) {
        User user = userRepository.findByEmail(orderDto.userEmail()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Order order = orderRepository.findById(orderDto.id()).orElseThrow(() -> new RuntimeException("Order Not Found"));
        orderRepository.delete(order);
    }*/

    public void changeOrder(OrderDto newOrder) {
        User user = userRepository.findByEmail(newOrder.userEmail()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Menu newMenu = menuRepository.findById(newOrder.menuId()).orElseThrow(() -> new RuntimeException("Menu Not Found"));

        Order newOrderEntity = OrderDto.toEntity(newOrder, user, newMenu);
        newOrderEntity.updateOrder(newOrder, user, newMenu);
        orderRepository.save(newOrderEntity);
    }

    public List<OrderDto> getAllOrders(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));
        return orderRepository.findByUserEmail(email).stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    public boolean deleteOrder(Integer orderId, String email) {
        Order order = orderRepository.findById(orderId)
                .filter(o -> o.getUser().getEmail().equals(email))
                .orElse(null);

        if (order != null) {
            orderRepository.delete(order);
            return true;
        }

        return false;
    }

    public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
        return OrderDto.fromEntity(order);
    }
}
