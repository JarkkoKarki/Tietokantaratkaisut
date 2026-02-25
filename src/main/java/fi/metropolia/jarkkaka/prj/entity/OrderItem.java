/*
package fi.metropolia.jarkkaka.prj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    private Integer quantity;
    private Double unit_price;
}
*/