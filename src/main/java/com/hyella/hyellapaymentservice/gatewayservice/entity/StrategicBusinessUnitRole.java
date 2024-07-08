package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractStrategicBusinessUnitRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table( name = "strategic_business_unit_role" )
@Getter
@Setter
@NoArgsConstructor
public class StrategicBusinessUnitRole extends AbstractStrategicBusinessUnitRole {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    public Long id;

    @ManyToOne
    public StrategicBusinessUnit strategicBusinessUnit;

    @ManyToOne
    @JoinColumn(name= "name")
    public Role role;

    @ManyToMany
    @JoinTable( name = "user_strategic_business_unit_role",
      joinColumns = @JoinColumn( name = "sbu_role_id" ),
      inverseJoinColumns = @JoinColumn( name = "user_id" )
    )
    private Set< User > userStrategicBusinessUnitRoles;

    @Column( name = "created_at", updatable = false, nullable = false )
    @CreationTimestamp
    private Date createdAt;

    @Column( name = "updated_at" )
    @UpdateTimestamp
    private Date updatedAt;

    @Column( name = "deleted" )
    private Boolean deleted;

    @Column( name = "enabled" )
    private Boolean enabled;

    public StrategicBusinessUnitRole( StrategicBusinessUnit strategicBusinessUnit, Role role, Set< User > userStrategicBusinessUnitRoles, Date createdAt, Date updatedAt, Boolean deleted, Boolean enabled ){
        this.strategicBusinessUnit = strategicBusinessUnit;
        this.role = role;
        this.userStrategicBusinessUnitRoles = userStrategicBusinessUnitRoles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.enabled = enabled;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled( boolean enabled ){
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
