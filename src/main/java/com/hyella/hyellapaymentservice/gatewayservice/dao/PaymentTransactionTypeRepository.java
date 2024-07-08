package com.hyella.hyellapaymentservice.gatewayservice.dao;

import org.springframework.data.domain.Page;
import com.hyella.hyellapaymentservice.gatewayservice.entity.PaymentTransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PaymentTransactionTypeRepository extends JpaRepository< PaymentTransactionType, Long >, JpaSpecificationExecutor< PaymentTransactionType > {
}
