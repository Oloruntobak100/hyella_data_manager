package com.hyella.hyellapaymentservice.gatewayservice.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hyella.hyellapaymentservice.gatewayservice.dao.OrganizationSbuPspMappingRepository;

@Service
public class OrganizationSbuPspMappingServiceImpl implements OrganizationSbuPspMappingService {

    private final OrganizationSbuPspMappingRepository mappingRepository;

    @Autowired
    public OrganizationSbuPspMappingServiceImpl( OrganizationSbuPspMappingRepository theMappingRepository ){
        mappingRepository = theMappingRepository;

    }


}