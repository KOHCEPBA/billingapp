package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Audit;
import ru.rudnick.billingapp.entity.Bill;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
