package ru.rudnick.billingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.entity.Type;
import ru.rudnick.billingapp.repository.AccountRepository;
import ru.rudnick.billingapp.repository.BillRepository;
import ru.rudnick.billingapp.util.exception.InvalidRequest;

import java.math.BigDecimal;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuditService auditService;

    public Bill createNewBill(Account account, BigDecimal amount) {
        if (amount.equals(BigDecimal.ZERO)) {
            throw new InvalidRequest("Amount must be different from 0");
        }
        Bill newBill = new Bill();
        newBill.setAccount(account);
        newBill.setAmount(amount);
        newBill.setType(amount.compareTo(BigDecimal.ZERO) < 0 ? Type.CREDIT : Type.DEBIT);
        return processBill(newBill);
    }

    public Bill processBill(Bill bill) {
        Account account = bill.getAccount();
        BigDecimal balance = account.getBalance();
        BigDecimal amount = bill.getAmount();
        switch (bill.getType()) {
            case CREDIT:
                if (balance.compareTo(amount) < 0) {
                    throw new InvalidRequest("Balance less than bill");
                }
                account.setBalance(balance.subtract(amount));
                break;
            case DEBIT:
                account.setBalance(balance.add(amount));
                break;
        }
        accountRepository.save(account);
        Bill savedBill = billRepository.save(bill);
        auditService.createAudit(bill);
        return savedBill;
    }
}
