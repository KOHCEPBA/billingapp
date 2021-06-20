package ru.rudnick.billingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.entity.Transaction;
import ru.rudnick.billingapp.repository.AuditRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class AuditService {

    @Autowired
    AuditRepository auditRepository;

    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }

    public Audit getAuditByBill(Long billId) {
        return auditRepository.findAuditByBillBillId(billId);
    }

    public List<Audit> getAuditByAccount(Long accountId) {
        List<Audit> list = new LinkedList<>();
        list.addAll(auditRepository.findAuditsByBillAccountAccountId(accountId));
        list.addAll(auditRepository.findAuditsByTransactionSourceAccountAccountId(accountId));
        list.addAll(auditRepository.findAuditsByTransactionTargetAccountAccountId(accountId));
        return list;
    }

    public Audit getAuditByTransaction(Long transactionId) {
        return auditRepository.findAuditByTransactionTransactionId(transactionId);
    }

    public Audit save(Audit audit) {
        return auditRepository.save(audit);
    }

    public Audit createAudit(Bill bill) {
        Audit audit = new Audit();
        audit.setBill(bill);
        audit.setAccount(bill.getAccount());
        return save(audit);
    }

    public Audit createAudit(Transaction transaction) {
        Audit audit = new Audit();
        audit.setTransaction(transaction);
        audit.setAccount(transaction.getSourceAccount());
        return save(audit);
    }
}
