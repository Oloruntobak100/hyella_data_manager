package com.hyella.hyellapaymentservice.gatewayservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;
import com.hyella.hyellapaymentservice.gatewayservice.entity.TransactionCharge;
// import com.hyella.hyellapaymentservice.gatewayservice.entity.PaymentServiceProvider;
// import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnit;

public interface TransactionChargeRepository extends JpaRepository< TransactionCharge, String > {


}
