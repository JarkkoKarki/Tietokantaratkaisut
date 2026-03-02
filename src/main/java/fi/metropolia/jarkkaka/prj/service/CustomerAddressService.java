package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.entity.CustomerAddress;
import fi.metropolia.jarkkaka.prj.entity.Customer;
import fi.metropolia.jarkkaka.prj.repository.CustomerAddressRepository;
import fi.metropolia.jarkkaka.prj.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public CustomerAddressService(CustomerAddressRepository addressRepository,
                                  CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public List<CustomerAddress> getAllAddresses() {
        return addressRepository.findAll();
    }

    public CustomerAddress getAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    public CustomerAddress addAddress(CustomerAddress address, Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return null;

        address.setCustomer(customer);
        return addressRepository.save(address);
    }

    public CustomerAddress updateAddress(Integer id, CustomerAddress updatedAddress, Integer customerId) {
        return addressRepository.findById(id).map(address -> {
            Customer customer = customerRepository.findById(customerId).orElse(address.getCustomer());
            address.setCustomer(customer);
            address.setStreetAddress(updatedAddress.getStreetAddress());
            address.setPostalcode(updatedAddress.getPostalcode());
            address.setCity(updatedAddress.getCity());
            address.setCountry(updatedAddress.getCountry());
            return addressRepository.save(address);
        }).orElse(null);
    }

    public boolean deleteAddress(Integer id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}