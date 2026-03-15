package fi.metropolia.jarkkaka.prj.repository;

import fi.metropolia.jarkkaka.prj.entity.NewOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewOrdersRepository extends JpaRepository<NewOrders, Integer> {

    @Query(value = "SELECT * FROM neworders WHERE first_name = :firstName LIMIT 100", nativeQuery = true)
    List<NewOrders> findByFirst_name(@Param("firstName") String firstName);
}
