package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractStrategicBusinessUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table( name = "strategic_business_unit" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StrategicBusinessUnit extends AbstractStrategicBusinessUnit {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;

    @Column( name = "name" )
    private String name;

    @ManyToOne
    @JoinColumn( name = "organization_id" )
    private Organization organization;

    @Column( name = "enabled" )
    private Boolean enabled;

    @Column( name = "phone_number" )
    private String phoneNumber;

    @Column( name = "email_address" )
    private String emailAddress;

    @Column( name = "address" )
    private String address;

    @ManyToOne
    private Country country;

    @Column( name = "created_at", updatable = false )
    @CreationTimestamp
    private Date createdAt;

    @Column( name = "updated_at" )
    @UpdateTimestamp
    private Date updatedAt;

    @Column( name = "created_by" )
    private String createdBy;

    @Column( name = "deleted" )
    private Boolean deleted;

    public StrategicBusinessUnit( String name, Organization organization, Boolean enabled, String phoneNumber, String emailAddress, String address, Country country, Date createdAt, String createdBy, Date updatedAt, Boolean deleted ){
        this.name = name;
        this.organization = organization;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.country = country;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }


    // public StrategicBusinessUnit(String name, Organization organization, Boolean enabled, String phoneNumber,
    //        String emailAddress, String address, Country country, long createdAt, long updatedAt, User createdBy,
    //        Boolean deleted) {
    //    this.name = name;
    //    this.organization = organization;
    //    this.enabled = enabled;
    //    this.phoneNumber = phoneNumber;
    //    this.emailAddress = emailAddress;
    //    this.address = address;
    //    this.country = country;
    //    this.createdAt = createdAt;
    //    this.updatedAt = updatedAt;
    //    this.createdBy = createdBy;
    //    this.deleted = deleted;
    //}

    public Long getId(){
        return id;
    }

    public void setId( Long id ){
        this.id = id;
    }

    public void setDeleted( boolean b ){
        this.deleted = b;
    }
}
