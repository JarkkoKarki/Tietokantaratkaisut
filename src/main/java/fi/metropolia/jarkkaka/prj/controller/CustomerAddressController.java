package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.Customer;
import fi.metropolia.jarkkaka.prj.entity.CustomerAddress;
import fi.metropolia.jarkkaka.prj.repository.CustomerAddressRepository;
import fi.metropolia.jarkkaka.prj.repository.CustomerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customeraddress")
public class CustomerAddressController {

    private final CustomerAddressRepository repository;
    private final CustomerRepository customerRepository;

    public CustomerAddressController(CustomerAddressRepository repository,
                                     CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<CustomerAddress> getAllAddresses() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddress> getCustomerAddressesById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CustomerAddress createCustomerAddress(@RequestBody CustomerAddress address) {

        Integer customerId = address.getCustomer().getId();

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        address.setCustomer(customer);

        return repository.save(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddress> updateCustomerAddress(
            @PathVariable Integer id,
            @RequestBody CustomerAddress updatedCustomerAddress) {

        return repository.findById(id)
                .map(customerAddress -> {
                    customerAddress.setCity(updatedCustomerAddress.getCity());
                    customerAddress.setCountry(updatedCustomerAddress.getCountry());
                    customerAddress.setCustomer(updatedCustomerAddress.getCustomer());
                    customerAddress.setPostalcode(updatedCustomerAddress.getPostalcode());
                    customerAddress.setStreetAddress(updatedCustomerAddress.getStreetAddress());

                    CustomerAddress saved = repository.save(customerAddress);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerAddress(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}