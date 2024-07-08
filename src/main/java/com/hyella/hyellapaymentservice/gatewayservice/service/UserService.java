package com.hyella.hyellapaymentservice.gatewayservice.service;

import com.hyella.hyellapaymentservice.gatewayservice.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    List< User > findAll();

    Optional< User > findByUserId( Long userId, Boolean deleted );

    User save( User theUser );

    void deleteUser( User theUser );

    // void enableUser(User theUser, boolean enabled);

    Page< User > getAllUsers( Boolean deleted, Pageable pageable );

    Page< User > getUsersByNameContaining( String name, Pageable pageable );

    Page< User > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable );

    Page< User > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable );

    User updateUser( Long userId, User theUser );
}
