package com.hyella.hyellapaymentservice.gatewayservice.rest;

import static com.hyella.hyellapaymentservice.common.constants.Constants.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;
import com.hyella.hyellapaymentservice.gatewayservice.entity.User;
import com.hyella.hyellapaymentservice.gatewayservice.service.UserService;
import com.hyella.hyellapaymentservice.gatewayservice.service.OrganizationService;

@RestController
@RequestMapping( DEFAULT_VERSION + SLASH + USER )
public class UserRestController {
    private final UserService userService;
    private final OrganizationService organizationService;

    @Autowired
    public UserRestController( final UserService theUserService, final OrganizationService theOganiOrganizationService ){
        this.userService = theUserService;
        this.organizationService = theOganiOrganizationService;
    }

    // Default mapping for an entity returns all objects.
    @RequestMapping( SLASH )
    public final ResponseEntity< ? > listUsers(){
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "Users retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", userService.findAll() );
        return ResponseEntity.ok( response );
    }

    @GetMapping( SLASH + "datatable-server" )
    public Page< User > getUsers(
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "name" ) String sortBy, // Default sort by name
      @RequestParam( defaultValue = "asc" ) String sortDirection, // Default sort direction ascending
      @RequestParam( required = false, defaultValue = "false" ) Boolean deleted, // default to false
      @RequestParam( required = false ) String keyword
    ){
        Sort.Direction direction = Sort.Direction.fromString( sortDirection );
        Pageable pageable = PageRequest.of( page, size, direction, sortBy );

        if( keyword != null && !keyword.isEmpty() ){
            return userService.findByKeywordIgnoreCase( keyword, deleted, pageable );
        }else{
            return userService.getAllUsers( deleted, pageable );
        }
    }

    @PostMapping( SLASH + "datatable-server" )
    public Page< User > filterUser(
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "name" ) String sortBy, // Default sort by name
      @RequestParam( defaultValue = "asc" ) String sortDirection, // Default sort direction ascending
      @RequestParam( required = false, defaultValue = "false" ) Boolean deleted, // default to false
      @RequestParam( required = false ) String keyword,
      @RequestBody Map< String, Object > inputJson ){

        /*Integer page = 0;
        Integer size = 10;
        String sortBy = "name";
        String sortDirection = "asc";
        Boolean deleted = false;*/

        Sort.Direction direction = Sort.Direction.fromString( sortDirection );
        Pageable pageable = PageRequest.of( page, size, direction, sortBy );

        if( inputJson != null && !inputJson.isEmpty() ){
            return userService.findByMultipleKeywordsIgnoreCase( inputJson, keyword, deleted, pageable );
        }else{
            return userService.getAllUsers( deleted, pageable );
        }
    }

    // Applying versioning for the list endpoint.
    @GetMapping( LIST )
    public final ResponseEntity< ? > listUsersv1(){
        return listUsers();
    }

    @GetMapping( SLASH + "{userId}" )
    public final ResponseEntity< ? > getUser( @PathVariable final Long userId ){
        Optional< User > theUser = userService.findByUserId( userId, false );
        if( theUser.isEmpty() ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND )
              .body( "User with user ID " + userId + " not found" );
        }
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "User Detail retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", theUser.get() );
        return ResponseEntity.ok( response );
    }

    @PostMapping( SLASH )
    public final ResponseEntity< ? > addUser( @RequestBody final User theUser ){
        theUser.setId( 0L );
        theUser.setDeleted( false );
       // Organization organizationId = theUser.getOrganization();
        Long organizationId = theUser.getOrganizationId();

        Organization existingOrganization = organizationService.findByOrganizationId( organizationId, false )
          .orElseThrow(
            () -> new NoSuchElementException( "Organization with ID " + organizationId + " not found" ) );
        theUser.setOrganization(existingOrganization);
        theUser.setCreatedBy("Kayode");
        User savedUser = userService.save( theUser );
        // User savedUser = theUser;

        if( savedUser != null && savedUser.getId() != null ){
            Map< String, Object > response = new HashMap<>();
            response.put( "status", "success" );
            response.put( "message", "User saved successfully" );
            response.put( "data", savedUser );
            return ResponseEntity.ok( response );
        }else{
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to save payment transaction" );
        }
    }

    @PutMapping( SLASH + "{userId}" )
    public final User updateUser( @PathVariable final Long userId, @RequestBody final User theUser ){
        return userService.updateUser( userId, theUser );
    }

    @DeleteMapping( SLASH + "{userId}" )
    public final ResponseEntity< ? > deleteUser( @PathVariable final Long userId ){
        Optional< User > theUser = userService.findByUserId( userId, false );

        Map< String, Object > response = new HashMap<>();

        if( theUser.isEmpty() ){
            throw new NoSuchElementException( "User with user ID " + userId + " not found" );
            /*ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("status","error");
            response.put("message", "User with user ID " + userId + " not found");*/
        }else{
            userService.deleteUser( theUser.get() );
            response.put( "message", "Users and SBUs successfully deleted" );
            response.put( "status", "success" );
            response.put( "data", theUser );
        }
        return ResponseEntity.ok( response );
    }

    // @PatchMapping(SLASH + "{userId}" + SLASH + "{enabled}")
    // public final ResponseEntity<?> enableUser(@PathVariable final Long userId,
    //         @PathVariable final Boolean enabled) {
    //     Optional<User> theUser = userService.findByUserId(userId, false);
    //     String status = "";
    //     Map<String, Object> response = new HashMap<>();

    //     if (theUser.isEmpty()) {
    //         throw new NoSuchElementException("User with user ID " + userId + " not found");
    //     } else {
    //         userService.enableUser(theUser.get(), enabled);
    //         status = enabled ? "enabled" : "disabled";
    //         response.put("message", "User and all user SBUs " + status);
    //         response.put("status", "success");
    //         //response.put("data", theUser);
    //     }

    //     return ResponseEntity.ok(response);
    // }

}
