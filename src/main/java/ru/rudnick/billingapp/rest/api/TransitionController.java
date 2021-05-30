package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Transaction;
import ru.rudnick.billingapp.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/request")
public class TransitionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable("id") Transaction request) {
        return request;
    }

    @PostMapping("/create")
    public Transaction createNewTransaction(@RequestParam("accountFromId") Account accountFrom,
                                            @RequestParam("accountToId") Account accountTo,
                                            @RequestParam("amount") BigDecimal amount) {
        return transactionService.createNewTransaction(accountFrom, accountTo, amount);
    }
}
