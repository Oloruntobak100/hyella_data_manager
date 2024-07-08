package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List< Currency > findAll();

    Optional< Currency > findByCurrencyCode( String currencyCode );

    // There are deliberately no APIs for currency
    // deletion and updates.
}
