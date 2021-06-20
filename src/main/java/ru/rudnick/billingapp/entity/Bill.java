package ru.rudnick.billingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    @ManyToOne
    @JoinColumn(name = "account")
    @NonNull
    private Account account;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private Type type;
}
