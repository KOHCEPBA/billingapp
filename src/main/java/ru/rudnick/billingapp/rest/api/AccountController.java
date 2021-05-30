package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.repository.AccountRepository;
import ru.rudnick.billingapp.service.BillService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository repository;

    @Autowired
    BillService billService;

    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable("id") Account account) {
        return account;
    }

    @PostMapping("/create")
    public Account createNewAccount() {
        Account account = new Account();
        repository.save(account);
        return account;
    }

    @PostMapping("/{id}/payment")
    public Bill changeBalance(@PathVariable("id") Account account, @RequestParam("amount") BigDecimal amount) {
        return billService.createNewBill(account, amount);
    }
}
