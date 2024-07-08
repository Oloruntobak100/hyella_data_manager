package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;

@Entity
@Table( name = "role" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role extends AbstractRole {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "name", unique = true )
    private String name;

    @Column( name = "created_at", updatable = false, nullable = false )
    @CreationTimestamp
    private Date createdAt;

    @Column( name = "updated_at" )
    @UpdateTimestamp
    private Date updatedAt;

    @Column( name = "deleted" )
    private Boolean deleted;

    @Column( name = "address" )
    private String address;

    @Column( name = "enabled" )
    private Boolean enabled;


    public Role( String name, Date createdAt, Date updatedAt, Boolean deleted, String address, Boolean enabled ){
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.address = address;
        this.enabled = enabled;
    }

    //public void setEnabled( boolean enabled ){
    //    this.enabled = enabled;
    //}

    //public void setDeleted( boolean b ){
    //    this.enabled = b;
    //}

    //public void setId(long l) {
    //}

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
