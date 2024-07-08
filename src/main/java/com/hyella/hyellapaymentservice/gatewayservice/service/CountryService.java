package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Country;
import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CountryService {
    List< Country > findAll();

    Optional< Country > findByCountryCode( String countryCode );

    Country save( Country theCountry );

    void deleteCountry( Country theCountry );

    Page< Country > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable );

    Page< Country > getAllCountry( Boolean deleted, Pageable pageable );

    Page< Country > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable );

    // There are deliberately no APIs for country
    // deletion and updates.
}
