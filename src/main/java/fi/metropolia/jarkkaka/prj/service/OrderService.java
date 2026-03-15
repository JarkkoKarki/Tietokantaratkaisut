package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.dto.OrderDto;
import fi.metropolia.jarkkaka.prj.dto.OrderTotalDto;
import fi.metropolia.jarkkaka.prj.entity.*;
import fi.metropolia.jarkkaka.prj.repository.CustomerAddressRepository;
import fi.metropolia.jarkkaka.prj.repository.CustomerRepository;
import fi.metropolia.jarkkaka.prj.repository.OrderRepository;
import fi.metropolia.jarkkaka.prj.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository addressRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        CustomerAddressRepository addressRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
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

    @Transactional
    public Order addOrder(OrderDto dto) {
        Order order = new Order();

        order.setCustomer(customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        order.setCustomeraddress(addressRepository.findById(dto.getCustomerAddressId())
                .orElse(null));

        order.setOrder_date();
        order.setDelivery_date(dto.getDeliveryDate());

        order.setStatus(dto.getStatus());

        List<OrderItem> items = dto.getOrderItems().stream().map(i -> {
            OrderItem oi = new OrderItem();
            Product product = productRepository.findById(i.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            oi.setProduct(product);
            oi.setQuantity(i.getQuantity());
            oi.setUnit_price(i.getUnit_price());
            oi.setOrder(order);
            return oi;
        }).collect(Collectors.toList());

        order.setOrderItems(items);

        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Integer id, OrderDto dto) {
        return orderRepository.findById(id).map(order -> {
            order.setCustomer(customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found")));
            order.setCustomeraddress(addressRepository.findById(dto.getCustomerAddressId())
                    .orElseThrow(() -> new RuntimeException("Customer address not found")));
            order.setDelivery_date(dto.getDeliveryDate());
            order.setStatus(dto.getStatus());

            List<OrderItem> existingItems = order.getOrderItems();

            existingItems.removeIf(oi -> dto.getOrderItems().stream()
                    .noneMatch(i -> i.getProductId().equals(oi.getProduct().getId())));

            for (var i : dto.getOrderItems()) {
                Optional<OrderItem> existing = existingItems.stream()
                        .filter(oi -> oi.getProduct().getId().equals(i.getProductId()))
                        .findFirst();
                if (existing.isPresent()) {
                    existing.get().setQuantity(i.getQuantity());
                    existing.get().setUnit_price(i.getUnit_price());
                } else {
                    OrderItem newItem = new OrderItem();
                    newItem.setOrder(order);
                    newItem.setProduct(productRepository.findById(i.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found")));
                    newItem.setQuantity(i.getQuantity());
                    newItem.setUnit_price(i.getUnit_price());
                    existingItems.add(newItem);
                }
            }

            return orderRepository.save(order);
        }).orElse(null);
    }

    public boolean deleteOrder(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}