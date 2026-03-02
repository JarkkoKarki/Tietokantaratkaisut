package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.Supplier;
import fi.metropolia.jarkkaka.prj.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) { this.service = service; }

    @GetMapping
    public List<Supplier> getAll() { return service.getAllSuppliers(); }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable Integer id) {
        Supplier s = service.getSupplierById(id);
        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(service.addSupplier(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Integer id, @RequestBody Supplier updated) {
        Supplier s = service.updateSupplier(id, updated);
        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.deleteSupplier(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}