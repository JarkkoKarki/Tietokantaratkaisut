package fi.metropolia.jarkkaka.prj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.metropolia.jarkkaka.prj.converter.OrderStatusConverter;
import fi.metropolia.jarkkaka.prj.enums.OrderStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    private Timestamp order_date;
    private Timestamp delivery_date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="shipping_address_id")
    @JsonIgnore
    private CustomerAddress customeraddress;
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerAddress getCustomeraddress() {
        return customeraddress;
    }

    public Timestamp getDelivery_date() {
        return delivery_date;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomeraddress(CustomerAddress customeraddress) {
        this.customeraddress = customeraddress;
    }

    public void setDelivery_date(Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrePersist
    public void setOrder_date() {
        this.order_date = new Timestamp(System.currentTimeMillis());
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}