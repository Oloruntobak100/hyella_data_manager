package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractPaymentServiceProvider;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "payment_service_provider")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentServiceProvider extends AbstractPaymentServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable( name = "payment_service_provider_transaction_type",
            joinColumns = @JoinColumn(name="payment_service_provider_id"),
            inverseJoinColumns = @JoinColumn(name="transaction_type_id"))
    private Set<PaymentTransactionType> paymentTransactionType;

    //@NotEmpty(message = "Transaction")
    @OneToMany(mappedBy = "paymentServiceProvider")
    private Set<TransactionCharge> transactionCharges;

    @Column(name="enabled")
    private Boolean enabled;

    public PaymentServiceProvider(String name, Set<PaymentTransactionType> paymentTransactionType,
            Set<TransactionCharge> transactionCharges, Boolean enabled) {
        this.name = name;
        this.paymentTransactionType = paymentTransactionType;
        this.transactionCharges = transactionCharges;
        this.enabled = enabled;
    }


}