package ru.rudnick.billingapp.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.service.AuditService;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    AuditService auditService;

    @GetMapping("/all")
    public List<Audit> getAllAudits() {
        return auditService.getAllAudits();
    }

    @GetMapping("/{id}")
    public Audit getAudit(@PathVariable("id") Audit audit) {
        return audit;
    }

    @GetMapping("/account/{id}")
    public List<Audit> getAuditByAccount(@PathVariable("id") Long accountId) {
        return auditService.getAuditByAccount(accountId);
    }

    @GetMapping("/transaction/{id}")
    public Audit getAuditByTransaction(@PathVariable("id") Long transactionId) {
        return auditService.getAuditByTransaction(transactionId);
    }

    @GetMapping("/bill/{id}")
    public Audit getAuditByBill(@PathVariable("id") Long billId) {
        return auditService.getAuditByBill(billId);
    }

}
