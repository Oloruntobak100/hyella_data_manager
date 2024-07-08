package com.hyella.hyellapaymentservice.gatewayservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;
import com.hyella.hyellapaymentservice.gatewayservice.entity.OrganizationSbuPspMapping;
import com.hyella.hyellapaymentservice.gatewayservice.entity.PaymentServiceProvider;
import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnit;

public interface OrganizationSbuPspMappingRepository extends JpaRepository< OrganizationSbuPspMapping, String > {

    List< OrganizationSbuPspMapping > findByOrganization( Organization organization );

    List< OrganizationSbuPspMapping > findByStrategicBusinessUnit( StrategicBusinessUnit sbu );

    Optional< OrganizationSbuPspMapping > findByStrategicBusinessUnitAndPaymentServiceProvider( StrategicBusinessUnit sbu, PaymentServiceProvider paymentServiceProvider );

    Optional< OrganizationSbuPspMapping > findByOrganizationAndPaymentServiceProvider( Organization organization, PaymentServiceProvider paymentServiceProvider );
}
