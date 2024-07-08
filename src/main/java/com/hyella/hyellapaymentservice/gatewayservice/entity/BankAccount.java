package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractBankAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table( name = "BankAccount" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankAccount extends AbstractBankAccount {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;

    @Column( name = "psp_reference_id" )
    private String pspRefId;

    @Column( name = "bank_name" )
    private String bankName;

    @Column( name = "account_number" )
    private Long accountNumber;

    @Column( name = "account_name" )
    private String accountName;

    @Column( name = "bank_code" )
    private Long bankCode;

    @Column( name = "type" )
    private String type;

    @ManyToOne
    @JoinColumn( name = "organization" )
    private Organization organizationId;

    @ManyToOne
    @JoinColumn( name = "sbu" )
    private StrategicBusinessUnit SbuId;

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

    @Column( name = "enabled" )
    private Boolean enabled;


    public BankAccount( String bankName, Long accountNumber, String accountName, Long bankCode, String type,
                        Organization organizationId, String pspRefId, StrategicBusinessUnit sbuId, Date createdAt, String createdBy, Date updatedAt, Boolean deleted,
                        Boolean enabled ){
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.bankCode = bankCode;
        this.type = type;
        this.organizationId = organizationId;
        this.pspRefId = pspRefId;
        this.SbuId = sbuId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.enabled = enabled;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public boolean isDeleted(){
        return deleted;
    }

    public void setEnabled( boolean enabled ){
        this.enabled = enabled;
    }

    public void setDeleted( boolean deleted ){
        this.deleted = deleted;
    }


    public Long getId(){
        return id;
    }

    public void setId( Long id ){
        this.id = id;
    }

}
