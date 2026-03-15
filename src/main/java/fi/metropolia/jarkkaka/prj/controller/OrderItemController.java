package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.dto.OrderItemDetailDto;
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
    public List<OrderItemDetailDto> getAll() {
        return service.getAllOrderItems().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDetailDto> getById(@PathVariable Integer id) {
        OrderItem i = service.getOrderItemById(id);
        if(i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(i));
    }

    @PostMapping
    public ResponseEntity<OrderItemDetailDto> create(@RequestBody OrderItemDetailDto dto) {
        try {
            OrderItem item = service.addOrderItem(dto);
            return ResponseEntity.ok(toDto(item));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Integer id, @RequestBody OrderItemDetailDto dto) {
        OrderItem updated = service.updateOrderItem(id, dto);
        if(updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if(service.deleteOrderItem(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    private OrderItemDetailDto toDto(OrderItem item) {
        OrderItemDetailDto dto = new OrderItemDetailDto();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnit_price());
        dto.setOrderId(item.getOrder() != null ? item.getOrder().getId() : null);
        dto.setProductId(item.getProduct() != null ? item.getProduct().getId() : null);

        return dto;
    }
}