package com.hyella.hyellapaymentservice.gatewayservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hyella.hyellapaymentservice.gatewayservice.entity.Country;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository< Country, String >, JpaSpecificationExecutor< Country > {

    List< Country > findAllByOrderByCountryNameAsc();

    Page< Country > findAll( Pageable pageable );

    Optional< Country > findBycountryCode( String countryCode );
}
