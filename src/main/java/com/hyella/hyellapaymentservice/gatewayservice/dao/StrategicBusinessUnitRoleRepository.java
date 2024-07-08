package com.hyella.hyellapaymentservice.gatewayservice.dao;

import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnitRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface StrategicBusinessUnitRoleRepository extends JpaRepository< StrategicBusinessUnitRole, Long >, JpaSpecificationExecutor< StrategicBusinessUnitRole > {

    List< StrategicBusinessUnitRole > findAllByOrderByStrategicBusinessUnitAsc();

    Page< StrategicBusinessUnitRole > findAll( Pageable pageable );

    Page< StrategicBusinessUnitRole > findByStrategicBusinessUnitContaining( String strategicBusinessUnit, Pageable pageable );

    Page< StrategicBusinessUnitRole > findByDeleted( Boolean deleted, Pageable pageable );

    Optional< StrategicBusinessUnitRole > findByIdAndDeleted( Long strategicbusinessunitroleId, Boolean deleted );
}
