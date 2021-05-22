package ru.rudnick.billingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long accountId;
    BigDecimal amount = BigDecimal.valueOf(0);
}
