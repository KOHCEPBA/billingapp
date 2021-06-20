package ru.rudnick.billingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.entity.Transaction;
import ru.rudnick.billingapp.entity.Type;
import ru.rudnick.billingapp.repository.TransitionRepository;
import ru.rudnick.billingapp.util.exception.InvalidRequest;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    BillService billService;
    @Autowired
    TransitionRepository transitionRepository;
    @Autowired
    AuditService auditService;

    public List<Transaction> getAllTransactions() {
        return transitionRepository.findAll();
    }

    public Transaction createNewTransaction(Account sourceAccount, Account targetAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 1) {
            throw new InvalidRequest("Amount must be more that 0");
        }
        if (sourceAccount.getAccountId().equals(targetAccount.getAccountId())) {
            throw new InvalidRequest("Only for different accounts");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setSourceAccount(sourceAccount);
        newTransaction.setTargetAccount(targetAccount);
        newTransaction.setAmount(amount);
        processBill(sourceAccount, amount, Type.CREDIT);
        processBill(targetAccount, amount, Type.DEBIT);
        Transaction transaction = transitionRepository.save(newTransaction);
        auditService.createAudit(transaction);
        return transaction;
    }

    private Bill processBill(Account account, BigDecimal amount, Type type) {
        return billService.processBill(new Bill(account, amount, type));
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transitionRepository.findAllBySourceAccount_AccountId(accountId);
    }


}
