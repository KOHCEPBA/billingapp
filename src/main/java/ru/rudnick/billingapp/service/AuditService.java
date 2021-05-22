package ru.rudnick.billingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Request;
import ru.rudnick.billingapp.repository.AuditRepository;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    AuditRepository auditRepository;

    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }

    public Audit getAuditByBill(Long billId) {
        return auditRepository.findAuditsByBillBillId(billId);
    }

    public Audit getAuditByRequest(Request request) {
        return auditRepository.findAuditsByRequestRequestId(request.getRequestId());
    }

    public Audit getAuditByRequest(Long billId) {
        return auditRepository.findAuditsByRequestRequestId(billId);
    }

    public Audit save(Audit audit) {
        return auditRepository.save(audit);
    }
}
