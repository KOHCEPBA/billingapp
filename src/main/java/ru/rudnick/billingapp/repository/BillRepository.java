package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Bill;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByAccount_AccountId(Long accountId);
}
