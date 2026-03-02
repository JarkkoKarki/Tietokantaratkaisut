package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.entity.Supplier;
import fi.metropolia.jarkkaka.prj.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository repo;

    public SupplierService(SupplierRepository repo) { this.repo = repo; }

    public List<Supplier> getAllSuppliers() { return repo.findAll(); }

    public Supplier getSupplierById(Integer id) { return repo.findById(id).orElse(null); }

    public Supplier addSupplier(Supplier supplier) { return repo.save(supplier); }

    public Supplier updateSupplier(Integer id, Supplier updated) {
        return repo.findById(id).map(s -> {
            s.setName(updated.getName());
            s.setProducts(updated.getProducts());
            return repo.save(s);
        }).orElse(null);
    }

    public boolean deleteSupplier(Integer id) {
        if (repo.existsById(id)) { repo.deleteById(id); return true; }
        return false;
    }
}