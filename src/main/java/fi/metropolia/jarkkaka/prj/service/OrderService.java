package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.dto.OrderTotalDto;
import fi.metropolia.jarkkaka.prj.entity.Order;
import fi.metropolia.jarkkaka.prj.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<OrderTotalDto> getSum(String email) {
        return orderRepository.getOrderTotalsByCustomerEmail(email);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        return orderOpt.orElse(null);
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Integer id, Order updatedOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomer(updatedOrder.getCustomer());
                    order.setCustomeraddress(updatedOrder.getCustomeraddress());
                    order.setOrder_date(updatedOrder.getOrder_date());
                    order.setDelivery_date(updatedOrder.getDelivery_date());
                    order.setStatus(updatedOrder.getStatus());
                    return orderRepository.save(order);
                })
                .orElse(null);
    }

    public boolean deleteOrder(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}