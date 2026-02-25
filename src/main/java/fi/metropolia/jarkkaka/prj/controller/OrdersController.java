package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.Orders;
import fi.metropolia.jarkkaka.prj.repository.OrdersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersRepository repository;

    public OrdersController(final OrdersRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Orders> getAllOrders() {return repository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Integer id) {
        return repository.findById(id).map(orders -> ResponseEntity.ok(orders)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        return repository.save(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Integer id, @RequestBody Orders updatedOrder) {
        return repository.findById(id)
                .map(order -> {
                    order.setCustomer(updatedOrder.getCustomer());
                    order.setOrder_date(updatedOrder.getOrder_date());
                    order.setDelivery_date(updatedOrder.getDelivery_date());
                    order.setStatus(updatedOrder.getStatus());
                    Orders saved = repository.save(order);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
