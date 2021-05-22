package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Account;
import ru.rudnick.billingapp.entity.Bill;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
