package fi.metropolia.jarkkaka.prj.repository;

import fi.metropolia.jarkkaka.prj.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
