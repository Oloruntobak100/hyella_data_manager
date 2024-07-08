package com.hyella.hyellapaymentservice.gatewayservice.dao;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository< Organization, Long >, JpaSpecificationExecutor< Organization > {

    List< Organization > findAllByOrderByNameAsc();

    Page< Organization > findAll( Pageable pageable );

    Page< Organization > findByNameContaining( String name, Pageable pageable );

    Page< Organization > findByAddressContainingIgnoreCase( String address, Pageable pageable );

    Page< Organization > findByDeleted( Boolean deleted, Pageable pageable );

    Optional< Organization > findByIdAndDeleted( Long organizationId, Boolean deleted );
}
