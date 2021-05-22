package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Bill;

import java.util.List;

public interface AuditRepository extends JpaRepository<Audit, Long> {

    Audit findAuditsByBillBillId(Long billId);

    Audit findAuditsByRequestRequestId(Long billId);

}
