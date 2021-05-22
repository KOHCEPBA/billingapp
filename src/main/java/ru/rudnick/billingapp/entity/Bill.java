package ru.rudnick.billingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long billId;
    @ManyToOne
    @JoinColumn(name = "accountFromId")
    Account from;
    @ManyToOne
    @JoinColumn(name = "accountToId")
    Account to;
    BigDecimal amount;
    @OneToOne
    @JoinColumn(name = "requestId")
    Request request;
}