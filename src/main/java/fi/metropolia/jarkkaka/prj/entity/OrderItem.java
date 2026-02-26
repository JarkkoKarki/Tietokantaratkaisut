package fi.metropolia.jarkkaka.prj.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orderitems")
public class OrderItem {
    @Id
    private Integer order_id;
    @Id
    private Integer product_id;
    private Integer quantity;
    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unit_price;

    public Integer getOrder_id() {
        return order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }
}