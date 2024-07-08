package com.hyella.hyellapaymentservice.gatewayservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.hyella.hyellapaymentservice.common.abstracts.AbstractPaymentTransactionType;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table( name = "payment_transaction_type" )
public class PaymentTransactionType extends AbstractPaymentTransactionType {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private int id;

    @Column( name = "transaction_type", unique = true )
    private String paymentTransactionType;

    @ManyToMany(mappedBy = "paymentTransactionType")
    private Set<PaymentServiceProvider> paymentServiceProviders;

    @Column(name = "deleted")
    private Boolean deleted;

    public PaymentTransactionType(String paymentTransactionType, Set<PaymentServiceProvider> paymentServiceProviders, Boolean deleted) {
        this.paymentTransactionType = paymentTransactionType;
        this.paymentServiceProviders = paymentServiceProviders;
        this.deleted = deleted;
    }
}