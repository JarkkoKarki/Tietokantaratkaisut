package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.OrderItem;
import fi.metropolia.jarkkaka.prj.entity.Order;
import fi.metropolia.jarkkaka.prj.entity.Product;
import fi.metropolia.jarkkaka.prj.repository.OrderItemRepository;
import fi.metropolia.jarkkaka.prj.repository.OrderRepository;
import fi.metropolia.jarkkaka.prj.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    private final OrderItemRepository orderItemRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderItemController(OrderItemRepository orderItemRepo,
                               OrderRepository orderRepo,
                               ProductRepository productRepo) {
        this.orderItemRepo = orderItemRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<OrderItem> getAllItems() {
        return orderItemRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getItemById(@PathVariable Integer id) {
        return orderItemRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderItem> createItem(@RequestBody OrderItem item) {
        Order order = orderRepo.findById(item.getOrder().getId()).orElse(null);
        Product product = productRepo.findById(item.getProduct().getId()).orElse(null);

        if (order == null || product == null) {
            return ResponseEntity.badRequest().build();
        }

        item.setOrder(order);
        item.setProduct(product);

        OrderItem saved = orderItemRepo.save(item);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateItem(@PathVariable Integer id, @RequestBody OrderItem updatedItem) {
        return orderItemRepo.findById(id)
                .map(item -> {
                    Order order = orderRepo.findById(updatedItem.getOrder().getId()).orElse(item.getOrder());
                    Product product = productRepo.findById(updatedItem.getProduct().getId()).orElse(item.getProduct());

                    item.setOrder(order);
                    item.setProduct(product);
                    item.setQuantity(updatedItem.getQuantity());
                    item.setUnit_price(updatedItem.getUnit_price());

                    OrderItem saved = orderItemRepo.save(item);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        if (orderItemRepo.existsById(id)) {
            orderItemRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}