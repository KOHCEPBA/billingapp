package ru.rudnick.billingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Audit")
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long auditId;
    @OneToOne
    @JoinColumn(name="requestId")
    Request request;
    @OneToOne
    @JoinColumn(name="billId")
    Bill bill;
    OffsetDateTime time;
}
