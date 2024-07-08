package com.hyella.hyellapaymentservice.gatewayservice.rest;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Currency;
import com.hyella.hyellapaymentservice.gatewayservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.hyella.hyellapaymentservice.common.constants.Constants.*;

@RestController
@RequestMapping( "/" + CURRENCY )
public class CurrencyRestController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyRestController( CurrencyService theCurrencyService ){
        this.currencyService = theCurrencyService;
    }

    //    Default mapping for an entity returns all objects
    @RequestMapping( SLASH )
    public List< Currency > listCurrencies(){
        return currencyService.findAll();
    }

    //    Applying versioning for the list endpoint.
    @GetMapping( DEFAULT_VERSION + LIST )
    public List< Currency > listCurrenciesv1(){
        return listCurrencies();
    }

    @GetMapping( DEFAULT_VERSION + "/{currencyCode}" )
    public Currency getCurrency( @PathVariable String currencyCode ){
        Optional< Currency > theCurrency = currencyService.findByCurrencyCode( currencyCode );
        if( theCurrency.isEmpty() ){
            throw new NoSuchElementException( "Currency with currency code " + currencyCode + " not found" );
        }
        return theCurrency.get();
    }
}
