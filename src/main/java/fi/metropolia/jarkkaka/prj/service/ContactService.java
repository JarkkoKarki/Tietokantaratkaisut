package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.entity.Contact;
import fi.metropolia.jarkkaka.prj.repository.ContactRepository;
import fi.metropolia.jarkkaka.prj.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;

    public ContactService(ContactRepository contactRepository,
                          CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Integer id) {
        Optional<Contact> opt = contactRepository.findById(id);
        return opt.orElse(null);
    }

    public Contact addContact(Contact contact) {
        if(contact.getCustomer() != null && contact.getCustomer().getId() != null) {
            contact.setCustomer(customerRepository.findById(contact.getCustomer().getId()).orElse(null));
        }
        return contactRepository.save(contact);
    }

    public Contact updateContact(Integer id, Contact updated) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setEmail(updated.getEmail());
                    contact.setReference(updated.getReference());
                    if(updated.getCustomer() != null && updated.getCustomer().getId() != null) {
                        contact.setCustomer(
                            customerRepository.findById(updated.getCustomer().getId()).orElse(contact.getCustomer())
                        );
                    }
                    return contactRepository.save(contact);
                })
                .orElse(null);
    }

    public boolean deleteContact(Integer id) {
        if(contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}