package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.repository.BillRepository;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillRepository repository;

    @GetMapping("/{id}")
    public Bill getBill(@PathVariable("id") Bill bill) {
        return bill;
    }

    @GetMapping("/all")
    public List<Bill> getAllBills() {
        return repository.findAll();
    }

    @GetMapping("/account/{id}")
    public List<Bill> getBillsByAccount(@PathVariable("id") Long accountId) {
        return repository.findAllByAccount_AccountId(accountId);
    }
}
