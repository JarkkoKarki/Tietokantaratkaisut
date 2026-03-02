package fi.metropolia.jarkkaka.prj.repository;

import fi.metropolia.jarkkaka.prj.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
