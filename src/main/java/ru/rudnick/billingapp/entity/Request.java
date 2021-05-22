package ru.rudnick.billingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Request")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestId;
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
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    public enum Status {
        OPEN,
        ACCEPT,
        REJECT
    }
}
