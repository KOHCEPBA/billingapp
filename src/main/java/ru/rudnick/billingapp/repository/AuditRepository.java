package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Bill;

import java.util.List;

public interface AuditRepository extends JpaRepository<Audit, Long> {

    Audit findAuditByBillBillId(Long billId);

    Audit findAuditByTransactionTransactionId(Long billId);

    List<Audit> findAuditsByBillAccountAccountId(Long accountId);
    List<Audit> findAuditsByTransactionSourceAccountAccountId(Long accountId);
    List<Audit> findAuditsByTransactionTargetAccountAccountId(Long accountId);

}
