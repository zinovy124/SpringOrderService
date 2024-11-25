package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @PostMapping
//    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
//        OrderDto createOrder = orderService.createOrder(orderDto);
//        return ResponseEntity.ok(createOrder);
//    }

    @PostMapping
    public RedirectView createOrder(@ModelAttribute OrderDto orderDto) {
        OrderDto order = orderService.createOrder(orderDto);
        return new RedirectView("/orders");
    }

    @GetMapping
    public ModelAndView viewAllOrders() {
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("orders", orderService.getAllOrders());
        return modelAndView;
    }

    @GetMapping("/change")
    public ModelAndView showChangeOrderForm(@RequestParam Integer orderId) {
        OrderDto order = orderService.getOrderById(orderId);
        String formattedDate = order.orderDate() != null
                ? order.orderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
                : "";
        ModelAndView modelAndView = new ModelAndView("change");
        modelAndView.addObject("order", order);
        modelAndView.addObject("formattedDate", formattedDate);
        return modelAndView;
    }

    @PostMapping("/change")
    public RedirectView redirectAfterChange(@ModelAttribute OrderDto orderDto) {
        OrderDto oldOrder = orderService.getOrderById(orderDto.id());
        orderService.changeOrder(orderDto);
        System.out.println(oldOrder.id() + " " + orderDto.id());
        return new RedirectView("/orders");
    }

    @PostMapping("/delete")
    public RedirectView deleteOrder(@RequestParam Integer orderId) {
        OrderDto orderDto = orderService.getOrderById(orderId);
        orderService.deleteOrder(orderDto);
        return new RedirectView("/orders");
    }
}
