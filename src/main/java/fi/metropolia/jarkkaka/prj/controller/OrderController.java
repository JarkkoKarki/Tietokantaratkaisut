package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.Order;
import fi.metropolia.jarkkaka.prj.repository.OrdersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrdersRepository repository;

    public OrderController(final OrdersRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Order> getAllOrders() {return repository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        return repository.findById(id).map(order -> ResponseEntity.ok(order)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return repository.save(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order updatedOrder) {
        return repository.findById(id)
                .map(order -> {
                    order.setCustomer(updatedOrder.getCustomer());
                    order.setOrder_date(updatedOrder.getOrder_date());
                    order.setDelivery_date(updatedOrder.getDelivery_date());
                    order.setStatus(updatedOrder.getStatus());
                    Order saved = repository.save(order);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}