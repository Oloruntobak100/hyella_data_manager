package com.hyella.hyellapaymentservice.gatewayservice.dao;

import com.hyella.hyellapaymentservice.gatewayservice.entity.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository< BankAccount, Long >, JpaSpecificationExecutor< BankAccount > {

    List< BankAccount > findAllByOrderByBankNameAsc();

    Page< BankAccount > findAll( Pageable pageable );

    Page< BankAccount > findByBankNameContaining( String name, Pageable pageable );

    Page< BankAccount > findBySbuId( Long SbuId, Pageable pageable );

    Page< BankAccount > findByBankNameContainingIgnoreCase( String bank_name, Pageable pageable );

    Page< BankAccount > findByDeleted( Boolean deleted, Pageable pageable );

    // Optional<BankAccount> findByIdAndDeleted(Long bankAccountId, Pageable deleted);

    // Optional<BankAccount> getAllSBUsForBankAccount(Long bankAccountId, Boolean deleted);

}
