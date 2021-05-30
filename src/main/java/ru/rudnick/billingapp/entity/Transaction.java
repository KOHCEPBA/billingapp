package ru.rudnick.billingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "sourceAccountId")
    private Account sourceAccount;
    @ManyToOne
    @JoinColumn(name = "targetAccountId")
    private Account targetAccount;
    private BigDecimal amount;
}
