package fi.metropolia.jarkkaka.prj.repository;

import fi.metropolia.jarkkaka.prj.dto.OrderTotalDto;
import fi.metropolia.jarkkaka.prj.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("""
       SELECT o.id, SUM(oi.unit_price * oi.quantity)
       FROM Order o
       JOIN OrderItem oi ON oi.order = o
       JOIN o.customer c
       WHERE c.email = :email
       GROUP BY o.id
       """)
    List<OrderTotalDto> getOrderTotalsByCustomerEmail(@Param("email") String email);
}
