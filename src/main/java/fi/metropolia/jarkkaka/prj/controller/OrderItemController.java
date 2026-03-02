package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.OrderItem;
import fi.metropolia.jarkkaka.prj.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderItem> getAll() {
        return service.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable Integer id) {
        OrderItem i = service.getOrderItemById(id);
        if(i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(i);
    }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem i) {
        return ResponseEntity.ok(service.addOrderItem(i));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Integer id, @RequestBody OrderItem i) {
        OrderItem updated = service.updateOrderItem(id, i);
        if(updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if(service.deleteOrderItem(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}