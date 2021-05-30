package ru.rudnick.billingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Bill")
@RequiredArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    @ManyToOne
    @JoinColumn(name = "accountId")
    @NonNull
    private Account account;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private Type type;
}
