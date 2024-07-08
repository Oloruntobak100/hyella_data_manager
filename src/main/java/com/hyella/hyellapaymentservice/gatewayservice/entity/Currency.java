package com.hyella.hyellapaymentservice.gatewayservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hyella.hyellapaymentservice.common.abstracts.AbstractCurrency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table( name = "currency" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Currency extends AbstractCurrency {
    @Id
    @Column( name = "code" )
    private String currencyCode;

    @ManyToMany
    @JsonIgnore
    @JoinTable( name = "currency_country",
      joinColumns = @JoinColumn( name = "currency_code" ),
      inverseJoinColumns = @JoinColumn( name = "country_code" )
    )
    private Set< Country > country;

    @Column( name = "name" )
    private String currencyName;

    @Column( name = "digits_after_decimal" )
    private int digitsAfterDecimal;
}
