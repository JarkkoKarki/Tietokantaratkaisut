package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.entity.OrderItem;
import fi.metropolia.jarkkaka.prj.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Integer id) {
        Optional<OrderItem> opt = orderItemRepository.findById(id);
        return opt.orElse(null);
    }

    public OrderItem addOrderItem(OrderItem item) {
        return orderItemRepository.save(item);
    }

    public OrderItem updateOrderItem(Integer id, OrderItem updatedItem) {
        return orderItemRepository.findById(id)
                .map(item -> {
                    item.setOrder(updatedItem.getOrder());
                    item.setProduct(updatedItem.getProduct());
                    item.setQuantity(updatedItem.getQuantity());
                    item.setUnit_price(updatedItem.getUnit_price());
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