package ru.rudnick.billingapp.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Bill")
@RequiredArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    @ManyToOne
    @JoinColumn(name = "accountFromId")
    @NonNull
    private Account from;
    @ManyToOne
    @JoinColumn(name = "accountToId")
    @NonNull
    private Account to;
    @NonNull
    private BigDecimal amount;
    @OneToOne
    @JoinColumn(name = "requestId")
    @NonNull
    private Request request;
}
