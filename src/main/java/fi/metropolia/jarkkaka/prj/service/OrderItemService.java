package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.dto.OrderItemDetailDto;
import fi.metropolia.jarkkaka.prj.dto.OrderItemDto;
import fi.metropolia.jarkkaka.prj.entity.Order;
import fi.metropolia.jarkkaka.prj.entity.OrderItem;
import fi.metropolia.jarkkaka.prj.entity.Product;
import fi.metropolia.jarkkaka.prj.repository.OrderItemRepository;
import fi.metropolia.jarkkaka.prj.repository.OrderRepository;
import fi.metropolia.jarkkaka.prj.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderItemRepository orderItemRepository,
                            OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Integer id) {
        Optional<OrderItem> opt = orderItemRepository.findById(id);
        return opt.orElse(null);
    }

    @Transactional
    public OrderItem addOrderItem(OrderItemDetailDto dto) {
        OrderItem item = new OrderItem();

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setUnit_price(dto.getUnitPrice() != null ? dto.getUnitPrice() : product.getPrice());

        return orderItemRepository.save(item);
    }

    @Transactional
    public OrderItem updateOrderItem(Integer id, OrderItemDetailDto dto) {
        return orderItemRepository.findById(id)
                .map(item -> {
                    Order order = orderRepository.findById(dto.getOrderId())
                            .orElseThrow(() -> new RuntimeException("Order not found"));

                    Product product = productRepository.findById(dto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    item.setOrder(order);
                    item.setProduct(product);
                    item.setQuantity(dto.getQuantity());
                    item.setUnit_price(dto.getUnitPrice());

                    return orderItemRepository.save(item);
                })
                .orElse(null);
    }

    public boolean deleteOrderItem(Integer id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}