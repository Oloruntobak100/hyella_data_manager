package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.BankAccount;
import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnit;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StrategicBusinessUnitService {

    List< StrategicBusinessUnit > findAll();

    Optional< StrategicBusinessUnit > findByStrategicBusinessUnitId( Long strategicbusinessunitId, Boolean deleted );

    StrategicBusinessUnit save( StrategicBusinessUnit theStrategicBusinessUnit );

    void deleteStrategicBusinessUnit( StrategicBusinessUnit theStrategicBusinessUnit );

    void enableStrategicBusinessUnit( StrategicBusinessUnit theStrategicBusinessUnit, boolean enabled );

    Page< StrategicBusinessUnit > getAllStrategicBusinessUnits( Boolean deleted, Pageable pageable );

    List< StrategicBusinessUnit > getAllSBUsForOrganization( Long organizationId );

    Page< StrategicBusinessUnit > getAllSBUsForStrategicBusinessUnit( Long strategicbusinessunitId );

    Page< StrategicBusinessUnit > getStrategicBusinessUnitsByNameContaining( String name, Pageable pageable );

    Page< StrategicBusinessUnit > findByAddressContainingIgnoreCase( String address, Pageable pageable );

    Page< StrategicBusinessUnit > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable );

    Page< StrategicBusinessUnit > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable );

    StrategicBusinessUnit updateStrategicBusinessUnit( Long strategicbusinessunitId,
                                                       StrategicBusinessUnit theStrategicBusinessUnit );

    List< StrategicBusinessUnit > getAllSBUsForBankAccount( Long bankAccountId );

    Page< StrategicBusinessUnit > getAllSBUsForBankAccount( Boolean deleted, Pageable pageable );

    // List<StrategicBusinessUnit> getAllSBUsForUser(Long userId);


    void deleteSBU( StrategicBusinessUnit sbu );


}
