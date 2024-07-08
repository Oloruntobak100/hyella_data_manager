package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnit;
import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnitRole;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StrategicBusinessUnitRoleService {

    List< StrategicBusinessUnitRole > findAll();

    Optional< StrategicBusinessUnitRole > findByStrategicBusinessUnitRoleId( Long strategicbusinessunitroleId, Boolean deleted );

    StrategicBusinessUnitRole save( StrategicBusinessUnitRole theStrategicBusinessUnitRole );

    void deleteStrategicBusinessUnitRole( StrategicBusinessUnitRole theStrategicBusinessUnitRole );

    void enableStrategicBusinessUnitRole( StrategicBusinessUnitRole theStrategicBusinessUnitRole, boolean enabled );


    Page< StrategicBusinessUnitRole > getAllStrategicBusinessUnitRoles( Boolean deleted, Pageable pageable );

    Page< StrategicBusinessUnitRole > getStrategicBusinessUnitRolesByNameContaining( String name, Pageable pageable );

    Page< StrategicBusinessUnitRole > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable );

    Page< StrategicBusinessUnitRole > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable );

    StrategicBusinessUnitRole updateStrategicBusinessUnitRole( Long strategicbusinessunitroleId, StrategicBusinessUnitRole theStrategicBusinessUnitRole );
}
