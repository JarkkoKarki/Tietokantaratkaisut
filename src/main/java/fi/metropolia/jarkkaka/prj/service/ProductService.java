package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.entity.Product;
import fi.metropolia.jarkkaka.prj.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public int increaseAllPrices(double percent) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Product> update = cb.createCriteriaUpdate(Product.class);
        Root<Product> root = update.from(Product.class);
        Path<BigDecimal> pricePath = root.get("price");
        BigDecimal factor = BigDecimal.valueOf(1 + percent / 100);
        Expression<BigDecimal> newPrice = cb.prod(pricePath, cb.literal(factor));
        update.set(pricePath, newPrice);

        return em.createQuery(update).executeUpdate();
    }


    public List<Product> getAllProducts() { return repo.findAll(); }

    public Product getProductById(Integer id) { return repo.findById(id).orElse(null); }

    public Product addProduct(Product product) { return repo.save(product); }

    public Product updateProduct(Integer id, Product updated) {
        return repo.findById(id).map(p -> {
            p.setName(updated.getName());
            p.setPrice(updated.getPrice());
            p.setSuppliers(updated.getSuppliers());
            return repo.save(p);
        }).orElse(null);
    }

    public boolean deleteProduct(Integer id) {
        if (repo.existsById(id)) { repo.deleteById(id); return true; }
        return false;
    }
}
