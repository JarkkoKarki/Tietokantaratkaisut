package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.OrderItem;
import fi.metropolia.jarkkaka.prj.dto.OrderItemDto;
import fi.metropolia.jarkkaka.prj.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderItemDto> getAll() {
        return service.getAllOrderItems().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getById(@PathVariable Integer id) {
        OrderItem i = service.getOrderItemById(id);
        if(i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(i));
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

    private OrderItemDto toDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnit_price());
        dto.setOrderId(item.getOrderId());
        dto.setProductId(item.getProductId());
        return dto;
    }
}