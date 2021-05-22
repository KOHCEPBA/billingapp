package ru.rudnick.billingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.entity.Request;
import ru.rudnick.billingapp.repository.AuditRepository;
import ru.rudnick.billingapp.repository.BillRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;
    @Autowired
    AuditService auditService;

    public Bill createNewBill (Account accountFrom, Account accountTo, BigDecimal amount, Request request){
        Bill newBill = new Bill(accountFrom, accountTo, amount, request);
        Bill bill = billRepository.saveAndFlush(newBill);
        Audit audit = auditService.getAuditByRequest(request);
        audit.setBill(bill);
        auditService.save(audit);
        return bill;
    }
}
