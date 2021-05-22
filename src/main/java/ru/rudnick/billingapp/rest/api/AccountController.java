package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.repository.AccountRepository;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountRepository repository;

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable Account account) {
        return account;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @PostMapping("/create/account")
    public Account createNewAccount() {
        Account account = new Account();
        repository.saveAndFlush(account);
        return account;
    }
}
