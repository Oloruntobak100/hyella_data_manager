package com.hyella.hyellapaymentservice.gatewayservice.dao;

import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface StrategicBusinessUnitRepository extends JpaRepository< StrategicBusinessUnit, Long >, JpaSpecificationExecutor< StrategicBusinessUnit > {

    List< StrategicBusinessUnit > findAllByOrderByNameAsc();

    Page< StrategicBusinessUnit > findAll( Pageable pageable );

    Page< StrategicBusinessUnit > findByNameContaining( String name, Pageable pageable );

    Page< StrategicBusinessUnit > findByAddressContainingIgnoreCase( String address, Pageable pageable );

    Page< StrategicBusinessUnit > findByDeleted( Boolean deleted, Pageable pageable );

    Optional< StrategicBusinessUnit > findByIdAndDeleted( Long strategicbusinessunitId, Boolean deleted );


}
