package fi.metropolia.jarkkaka.prj.controller;

import fi.metropolia.jarkkaka.prj.entity.NewOrders;
import fi.metropolia.jarkkaka.prj.service.NewOrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/views")
public class NewOrdersController {

    private final NewOrdersService service;

    public NewOrdersController(NewOrdersService service) {
        this.service = service;
    }

    @GetMapping
    public List<NewOrders> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<NewOrders> searchByFirstName(@RequestParam String first_name) {
        return service.findByName(first_name);
    }
}
