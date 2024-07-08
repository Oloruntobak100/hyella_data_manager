package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.hyella.hyellapaymentservice.common.abstracts.AbstractCountry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table( name = "country" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Country extends AbstractCountry {
    @Id
    @Column( name = "code", unique = true )
    private String countryCode;

    @Column( name = "name", unique = true )
    private String countryName;

    @Column( name = "deleted" )
    private Boolean deleted;

    // Using the many-to-Many relationship here to support countries where multiple currencies are accepted(e.g. USD + local currency)
    @ManyToMany( cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinTable( name = "currency_country",
      joinColumns = @JoinColumn( name = "country_code" ),
      inverseJoinColumns = @JoinColumn( name = "currency_code" ) )
    private Set< Currency > currency;
}

