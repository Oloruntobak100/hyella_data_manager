package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractTransactionCharge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import com.hyella.hyellapaymentservice.common.enums.TransactionChargeTypeEnum;
import com.hyella.hyellapaymentservice.common.enums.TransactionChargeRecepientEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table( name = "country_transaction_charge" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransactionCharge extends AbstractTransactionCharge {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "transaction_charge_id" )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "payment_service_provider_id" )
    private PaymentServiceProvider paymentServiceProvider;

    @ManyToOne
    @JoinColumn( name = "transaction_type_id" )
    private PaymentTransactionType paymentTransactionType;

    @ManyToOne
    @JoinColumn( name = "country" )
    private Country country;

    @Enumerated(EnumType.STRING)
    @Column(name = "charge_type")
    private TransactionChargeTypeEnum chargeType;

    @Column(name = "service_charge_amount")
    private double serviceChargeAmount;


    @Column(name = "service_charge_percent")
    private double serviceChargePercent;


    @Enumerated(EnumType.STRING)
    @Column(name = "recipient_type")
    private TransactionChargeRecepientEnum recipientType;

    @Column( name = "service_charge" )
    private double serviceCharge;


    @Column(name ="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

}