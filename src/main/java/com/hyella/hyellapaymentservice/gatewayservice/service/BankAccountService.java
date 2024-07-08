package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.BankAccount;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankAccountService {


    List< BankAccount > findAll();

    // Optional<BankAccount> findByBankAccountId(Long bankAccountId, Pageable deleted);

    BankAccount save( BankAccount theBankAccount );

    void deleteBankAccount( BankAccount theBankAccount );

    void enableBankAccount( BankAccount theBankAccount, boolean enabled );

    Page< BankAccount > getAllBankAccounts( Boolean deleted, Pageable pageable );

    Page< BankAccount > findByBankNameContaining( String bank_name, Pageable pageable );

    Page< BankAccount > findBySbuId( Long SbuId, Pageable pageable );

    Page< BankAccount > findByBankNameContainingIgnoreCase( String BankName, Pageable pageable );

    Page< BankAccount > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable );

    Page< BankAccount > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable );

    BankAccount updateBankAccount( Long bankAccountId, BankAccount theBankAccount );
}
