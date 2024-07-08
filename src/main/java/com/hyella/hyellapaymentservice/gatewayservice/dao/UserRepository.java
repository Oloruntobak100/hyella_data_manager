package com.hyella.hyellapaymentservice.gatewayservice.dao;

import com.hyella.hyellapaymentservice.gatewayservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository< User, Long >, JpaSpecificationExecutor< User > {

    List< User > findAllByOrderByNameAsc();

    Page< User > findAll( Pageable pageable );

    Page< User > findByNameContaining( String name, Pageable pageable );

    Page< User > findByDeleted( Boolean deleted, Pageable pageable );

    Optional< User > findByIdAndDeleted( Long userId, Boolean deleted );
}
