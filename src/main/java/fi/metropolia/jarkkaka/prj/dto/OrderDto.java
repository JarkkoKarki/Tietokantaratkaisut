package fi.metropolia.jarkkaka.prj.dto;

import fi.metropolia.jarkkaka.prj.converter.OrderStatusConverter;
import fi.metropolia.jarkkaka.prj.enums.OrderStatus;

import java.sql.Timestamp;

import java.util.List;

public class OrderDto {
    private Integer customerId;
    private Integer customerAddressId;
    private Timestamp orderDate;
    private Timestamp deliveryDate;
    private OrderStatus status;
    private List<OrderItemDto> orderItems;

    // getterit ja setterit camelCase
    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
    public Timestamp getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(Timestamp deliveryDate) { this.deliveryDate = deliveryDate; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public Integer getCustomerAddressId() { return customerAddressId; }
    public void setCustomerAddressId(Integer customerAddressId) { this.customerAddressId = customerAddressId; }
    public List<OrderItemDto> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemDto> orderItems) { this.orderItems = orderItems; }
}