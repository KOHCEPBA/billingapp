package ru.rudnick.billingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rudnick.billingapp.entity.*;
import ru.rudnick.billingapp.repository.AccountRepository;
import ru.rudnick.billingapp.repository.AuditRepository;
import ru.rudnick.billingapp.repository.TransitionRepository;
import ru.rudnick.billingapp.util.exception.InvalidRequest;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    BillService billService;
    @Autowired
    TransitionRepository requestRepository;
    @Autowired
    AuditService auditService;

    public List<Transaction> getAllTransactions() {
        return requestRepository.findAll();
    }

    public Transaction createNewTransaction(Account sourceAccount, Account targetAccount, BigDecimal amount) {
        if (amount.equals(BigDecimal.ZERO)) {
            throw new InvalidRequest("Amount must be different from 0");
        }
        if (sourceAccount.getAccountId().equals(targetAccount.getAccountId())) {
            throw new InvalidRequest("Only for different accounts");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setSourceAccount(sourceAccount);
        newTransaction.setTargetAccount(targetAccount);
        newTransaction.setAmount(amount);
        boolean isCredit = amount.compareTo(BigDecimal.ZERO) < 0;
        processBill(sourceAccount, amount, isCredit ? Type.CREDIT : Type.DEBIT);
        processBill(sourceAccount, amount, isCredit ? Type.DEBIT : Type.CREDIT);
        Transaction transaction = requestRepository.save(newTransaction);
        auditService.createAudit(transaction);
        return transaction;
    }

    private Bill processBill(Account account, BigDecimal amount, Type type) {
        return billService.processBill(new Bill(account, amount, type));
    }


}
