package ru.rudnick.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rudnick.billingapp.entity.Bill;
import ru.rudnick.billingapp.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
