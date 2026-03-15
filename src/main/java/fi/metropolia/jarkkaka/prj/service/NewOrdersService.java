package fi.metropolia.jarkkaka.prj.service;

import fi.metropolia.jarkkaka.prj.entity.NewOrders;
import fi.metropolia.jarkkaka.prj.repository.NewOrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewOrdersService {
    private final NewOrdersRepository repository;

    public NewOrdersService(NewOrdersRepository repository) {
        this.repository = repository;
    }

    public List<NewOrders> getAll() {
        return repository.findAll();
    }

    public List<NewOrders> findByName(String first_name) {
        return repository.findByFirst_name(first_name);
    }
}