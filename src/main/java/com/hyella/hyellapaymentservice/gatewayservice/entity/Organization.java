package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractOrganization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;


@Entity
@Table( name = "organization" )
@Getter
@Setter
//@NoArgsConstructor
@ToString
public class Organization extends AbstractOrganization {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;

    @Column( name = "name" )
    private String name;

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

    @CreationTimestamp
    @Column( name = "created_at", updatable = false, nullable = false )
    private Date createdAt;

    @Column( name = "created_by" )
    private String createdBy;

    @Column( name = "updated_at" )
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany( cascade = {
      CascadeType.ALL
    } )
    private Set< StrategicBusinessUnit > StrategicBusinessUnits;



    @Column( name = "deleted" )
    private Boolean deleted;

    public Organization(){
    }


    public Organization( String name, Boolean enabled, String phoneNumber, String emailAddress, String address, Country country, Date createdAt, String createdBy, Date updatedAt, Set< StrategicBusinessUnit > strategicBusinessUnits, Boolean deleted){
        this.name = name;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.country = country;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        StrategicBusinessUnits = strategicBusinessUnits;
        this.deleted = deleted;
    }


    // Disabled all SBUs that belong to an organization before disabling the
    // organization
    //    public void setEnabled(Boolean enabled) {
    //        if(  this.getStrategicBusinessUnits() != null ) {
    //            for (StrategicBusinessUnit sbu : this.getStrategicBusinessUnits()) {
    //                sbu.setEnabled(enabled);
    //            }
    //        }
    //        this.enabled = enabled;
    //    }

    //    private StrategicBusinessUnit[] getStrategicBusinessUnits() {
    //        return
    //    }

    public Long getId(){
        return id;
    }

    public void setId( Long id ){
        this.id = id;
    }

    public void setEnabled( boolean enabled ){
        this.enabled = enabled;
    }
}
