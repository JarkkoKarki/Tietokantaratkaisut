package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.dto.OrderDto;
import fi.metropolia.jarkkaka.prj.dto.OrderTotalDto;
import fi.metropolia.jarkkaka.prj.entity.*;
import fi.metropolia.jarkkaka.prj.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService order) {
        this.orderService = order;
    }

    @GetMapping("/total/{email}")
    public List<OrderTotalDto> getTotals(@PathVariable String email) {
        return orderService.getSum(email);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        try {
            Order savedOrder = orderService.addOrder(orderDto);
            return ResponseEntity.ok(savedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody OrderDto updatedOrder) {
        Order updated = orderService.updateOrder(id, updatedOrder);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        boolean deleted = orderService.deleteOrder(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}