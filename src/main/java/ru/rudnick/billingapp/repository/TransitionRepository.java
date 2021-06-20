package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Transaction;

import java.util.List;

public interface TransitionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySourceAccount_AccountId(Long accountId);
}
