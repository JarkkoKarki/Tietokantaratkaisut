package fi.metropolia.jarkkaka.prj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Table(name = "neworders")
@Immutable
public class NewOrders {
    @Id
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String name;
    private String status;
    private Integer quantity;
    private BigDecimal unit_price;
    private BigDecimal total;


    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
