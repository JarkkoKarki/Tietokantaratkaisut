package fi.metropolia.jarkkaka.prj.repository;

import fi.metropolia.jarkkaka.prj.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
