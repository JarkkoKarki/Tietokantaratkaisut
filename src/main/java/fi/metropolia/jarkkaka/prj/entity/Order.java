package fi.metropolia.jarkkaka.prj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    private Timestamp order_date;
    private Timestamp delivery_date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="shipping_address_id")
    @JsonBackReference
    private CustomerAddress customeraddress;
    private String status;

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

    public String getStatus() {
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

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}