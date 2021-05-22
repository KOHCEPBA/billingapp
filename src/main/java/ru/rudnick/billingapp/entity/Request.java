package ru.rudnick.billingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Request")
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long requestId;
    @ManyToOne
    @JoinColumn(name="accountFromId")
    Account from;
    @ManyToOne
    @JoinColumn(name="accountToId")
    Account to;
    BigDecimal amount;
}
