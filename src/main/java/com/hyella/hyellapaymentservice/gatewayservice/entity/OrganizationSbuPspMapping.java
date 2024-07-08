package com.hyella.hyellapaymentservice.gatewayservice.entity;


import java.util.Date;
import java.util.HashMap;

// import org.hibernate.mapping.Set;
import java.util.Set;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractOrganizationSbuPspMapping;
import com.hyella.hyellapaymentservice.gatewayservice.config.HashMapConverter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table( name = "organization_payment_service_provider_mapping" )
@NoArgsConstructor
@Getter
@Setter

public class OrganizationSbuPspMapping extends AbstractOrganizationSbuPspMapping {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private String id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "organization" )
    private Organization organization;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "strategic_business_unit" )
    private StrategicBusinessUnit strategicBusinessUnit;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "payment_service_provider" )
    private PaymentServiceProvider paymentServiceProvider;

    @Column( name = "metadata" )
    @Convert( converter = HashMapConverter.class )
    private HashMap< String, Object > metadata;

    @Column( name = "payment_method" )
    private String paymentMethod;

    @Column( name = "bank_account_payout" )
    private Long bankAccountPayout;

    @OneToOne( cascade = {
      CascadeType.ALL
    } )
    private BankAccount bankAccountPayoutDetails;


    @Column( name = "bank_account_customer" )
    private Long bankAccountCustomer;

    @ManyToOne
    @JoinColumn( name = "bank_account_hyella" )
    private BankAccount bankAccountHyella;

    @ManyToOne
    @JoinColumn( name = "transaction_charge_hyella" )
    private TransactionCharge transactionChargeHyella;

    @ManyToOne
    @JoinColumn( name = "transaction_charge_psp" )
    private TransactionCharge transactionChargePSP;

    @Column( name = "created_at", updatable = false, nullable = false )
    @CreationTimestamp
    private Date createdAt;

    @Column( name = "created_by" )
    private String createdBy;

    @Column( name = "updated_at" )
    @UpdateTimestamp
    private Date updatedAt;

    @Column( name = "deleted" )
    private Boolean deleted;

    public OrganizationSbuPspMapping( Organization organization, StrategicBusinessUnit strategicBusinessUnit, PaymentServiceProvider paymentServiceProvider, HashMap< String, Object > metadata, String paymentMethod, Long bankAccountPayout, BankAccount bankAccountPayoutDetails, Long bankAccountCustomer, BankAccount bankAccountHyella, TransactionCharge transactionChargeHyella, TransactionCharge transactionChargePSP, Date createdAt, String createdBy, Date updatedAt, Boolean deleted ){
        this.organization = organization;
        this.strategicBusinessUnit = strategicBusinessUnit;
        this.paymentServiceProvider = paymentServiceProvider;
        this.metadata = metadata;
        this.paymentMethod = paymentMethod;
        this.bankAccountPayout = bankAccountPayout;
        this.bankAccountPayoutDetails = bankAccountPayoutDetails;
        this.bankAccountCustomer = bankAccountCustomer;
        this.bankAccountHyella = bankAccountHyella;
        this.transactionChargeHyella = transactionChargeHyella;
        this.transactionChargePSP = transactionChargePSP;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }


}

/**
 * Constructs a new OrganizationPspMapping object with the specified parameters.
 * This constructor initializes an instance of OrganizationPspMapping with the provided values.
 *
 * @param organization            The organization associated with the mapping.
 * @param strategicBusinessUnit   The strategic business unit associated with the mapping.
 * @param paymentServiceProvider The payment service provider associated with the mapping.
 * @param metadata                   The value associated with the mapping.
 */
// public OrganizationSbuPspMapping(Organization organization, StrategicBusinessUnit strategicBusinessUnit, PaymentServiceProvider paymentServiceProvider, HashMap<String, Object> metadata){
//     this.organization = organization;
//     this.strategicBusinessUnit = strategicBusinessUnit;
//     this.paymentServiceProvider = paymentServiceProvider;
//     this.metadata = metadata;
// }


