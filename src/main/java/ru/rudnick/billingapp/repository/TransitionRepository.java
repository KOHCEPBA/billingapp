package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Transaction;

public interface TransitionRepository extends JpaRepository<Transaction, Long> {
}
