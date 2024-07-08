package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizationService {

    List< Organization > findAll();

    Optional< Organization > findByOrganizationId( Long organizationId, Boolean deleted );

    Organization save( Organization theOrganization );

    void deleteOrganization( Organization theOrganization );

    void enableOrganization( Organization theOrganization, boolean enabled );

    Page< Organization > getAllOrganizations( Boolean deleted, Pageable pageable );

    Page< Organization > getOrganizationsByNameContaining( String name, Pageable pageable );

    Page< Organization > findByAddressContainingIgnoreCase( String address, Pageable pageable );

    Page< Organization > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable );

    Page< Organization > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable );

    Organization updateOrganization( Long organizationId, Organization theOrganization );
}
