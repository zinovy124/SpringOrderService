package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /*@PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(createOrder);
    }*/

    /*@PostMapping
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
    }*/
    @PostMapping("/order")
    public ResponseEntity<String> placeOrder(
            @RequestBody OrderDto orderDto,
            HttpSession session
    ) {
        System.out.println(orderDto.id() + " " + orderDto.menuId() + " " + orderDto.userEmail());
        String email = (String) session.getAttribute("user");
        if (email == null) {
            System.out.println("Fuck you");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must logged in to place an order.");
        }

        OrderDto order = orderService.createOrder(orderDto, email);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Failed");
        } else {
            return ResponseEntity.ok("Order Successfully Placed");
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> getUserOrders(HttpSession session) {
        String email = (String) session.getAttribute("user");
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<OrderDto> userOrders = orderService.getAllOrders(email);
        return ResponseEntity.ok(userOrders);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer orderId, HttpSession session) {
        String email = (String) session.getAttribute("user");
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to delete an order");
        }

        boolean deleted = orderService.deleteOrder(orderId, email);

        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found or not authorized");
        }
    }
}
