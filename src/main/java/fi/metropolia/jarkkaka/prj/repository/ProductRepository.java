package fi.metropolia.jarkkaka.prj.repository;

import fi.metropolia.jarkkaka.prj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
