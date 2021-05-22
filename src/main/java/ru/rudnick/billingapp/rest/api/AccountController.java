package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.repository.AccountRepository;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository repository;

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
}
