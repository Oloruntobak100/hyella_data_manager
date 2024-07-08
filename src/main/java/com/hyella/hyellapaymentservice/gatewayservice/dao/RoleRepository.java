package com.hyella.hyellapaymentservice.gatewayservice.dao;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository< Role, Long >, JpaSpecificationExecutor< Role > {

    List< Role > findAllByOrderByNameAsc();

    Page< Role > findAll( Pageable pageable );

    Page< Role > findByNameContaining( String name, Pageable pageable );

    Page< Role > findByAddressContainingIgnoreCase( String address, Pageable pageable );

    Page< Role > findByDeleted( Boolean deleted, Pageable pageable );

    Optional< Role > findByIdAndDeleted( Long organizationId, Boolean deleted );
}
