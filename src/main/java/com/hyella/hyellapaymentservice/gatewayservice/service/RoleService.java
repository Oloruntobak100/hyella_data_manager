package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Role;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    List< Role > findAll();

    Optional< Role > findByRoleId( Long roleId, Boolean deleted );


    Role save( Role theRole );

    void deleteRole( Role theRole );

    void enableRole( Role theRole, boolean enabled );


    Page< Role > getAllRoles( Boolean deleted, Pageable pageable );

    Page< Role > getRolesByNameContaining( String name, Pageable pageable );

    Page< Role > findByAddressContainingIgnoreCase( String address, Pageable pageable );

    Page< Role > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable );

    Page< Role > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable );

    Role updateRole( Long roleId, Role theRole );
}
