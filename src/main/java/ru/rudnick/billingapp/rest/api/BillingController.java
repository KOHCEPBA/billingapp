package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.repository.BillRepository;

import java.util.List;

@RestController
public class BillingController {

    @Autowired
    BillRepository repository;

    @GetMapping("/bill/{id}")
    public Bill getBill(@PathVariable Bill bill) {
        return bill;
    }

    @GetMapping("/bills")
    public List<Bill> getAllBills() {
        return repository.findAll();
    }
}
