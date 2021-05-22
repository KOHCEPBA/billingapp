package ru.rudnick.billingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Audit")
@Data
@NoArgsConstructor
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long auditId;
    @OneToOne
    @JoinColumn(name = "requestId")
    private Request request;
    @OneToOne
    @JoinColumn(name = "billId")
    private Bill bill;
    private OffsetDateTime time = OffsetDateTime.now();
}
