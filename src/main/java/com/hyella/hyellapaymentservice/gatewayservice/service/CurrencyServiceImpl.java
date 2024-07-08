package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.dao.CurrencyRepository;
import com.hyella.hyellapaymentservice.gatewayservice.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl( final CurrencyRepository theCurrencyRepository ){
        this.currencyRepository = theCurrencyRepository;
    }

    @Override
    public final List< Currency > findAll(){
        return currencyRepository.findAllByOrderByCurrencyNameAsc();
    }

    @Override
    public final Optional< Currency > findByCurrencyCode( final String currencyCode ){
        return currencyRepository.findById( currencyCode );
    }
}
