package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table( name = "user" )
@Getter
@Setter
//@NoArgsConstructor
@ToString
public class User extends AbstractUser {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;

    @Column( name = "first_name" )
    private String name;

    @Column( name = "last_name" )
    private String lastName;

    @Transient
    private Long organizationId;

    @ManyToOne
    @JoinColumn( name = "organization_id" )
    private Organization organization;

    @Column( name = "source_system_user_id" )
    private String sourceSystemUserId;

    @Column( name = "created_at", updatable = false, nullable = false )

    @CreationTimestamp
    private Date createdAt;

    @Column( name = "updated_at" )

    @UpdateTimestamp
    private Date updatedAt;

    @ManyToMany
    @JoinTable( name = "user_strategic_business_unit_role",
      joinColumns = @JoinColumn( name = "user_id" ),
      inverseJoinColumns = @JoinColumn( name = "sbu_role_id" ) )
    private Set< StrategicBusinessUnitRole > strategicBusinessUnitRoles;


    @Column( name = "created_by" )
    private String createdBy;

    @Column( name = "deleted" )
    private Boolean deleted;

    @Column( name = "enabled" )
    private Boolean enabled;

    public User() {
        // no-argument constructor
    }



    public User( String name, String lastName, Organization organization, String sourceSystemUserId,
                 Date createdAt, Date updatedAt, Set< StrategicBusinessUnitRole > strategicBusinessUnitRoles, String createdBy,
                 Boolean deleted, Boolean enabled){
        this.name = name;
        this.lastName = lastName;
        this.organization = organization;
        this.sourceSystemUserId = sourceSystemUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.strategicBusinessUnitRoles = strategicBusinessUnitRoles;
        this.createdBy = createdBy;
        this.deleted = deleted;
        this.enabled = enabled;
    }

    //public Long getId(){
    //    return id;
    //}
    //
    //public void setId( long l ){
    //    this.id = l;
    //}

    //public void setDeleted( boolean b ){
    //    this.deleted = b;
    //}


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Organization getOrganization(){
        return organization;
    }

    public void setOrganization( Organization organization ){
        this.organization = organization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSourceSystemUserId(String sourceSystemUserId) {
        this.sourceSystemUserId = sourceSystemUserId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStrategicBusinessUnitRoles(Set<StrategicBusinessUnitRole> strategicBusinessUnitRoles) {
        this.strategicBusinessUnitRoles = strategicBusinessUnitRoles;
    }

    public void setOrganization(long l) {
    }

    // public com.hyella.hyellapaymentservice.gatewayservice.entity.Organization getOrganization() {
    //    return organization;
    //}
    //
    // public void setOrganization(com.hyella.hyellapaymentservice.gatewayservice.entity.Organization organizationId) {
    //    this.organization = organizationId;
    //}
}
