package com.hyella.hyellapaymentservice.gatewayservice.rest;

import static com.hyella.hyellapaymentservice.common.constants.Constants.*;

import java.lang.reflect.Field;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;
import com.hyella.hyellapaymentservice.gatewayservice.service.OrganizationService;

@RestController
@RequestMapping( DEFAULT_VERSION + SLASH + ORGANIZATION )
public class OrganizationRestController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationRestController( final OrganizationService theOrganizationService ){
        this.organizationService = theOrganizationService;
    }

    // Default mapping for an entity returns all objects.
    @RequestMapping( SLASH )
    public final ResponseEntity< ? > listOrganizations(){
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "Organizations retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", organizationService.findAll() );
        return ResponseEntity.ok( response );
    }

    @GetMapping( SLASH + "datatable-server" )
    public Page< Organization > getOrganizations(
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
            return organizationService.findByKeywordIgnoreCase( keyword, deleted, pageable );
        }else{
            return organizationService.getAllOrganizations( deleted, pageable );
        }
    }

    @PostMapping( SLASH + "datatable-server" )
    public Page< Organization > filterOrganization(
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
            return organizationService.findByMultipleKeywordsIgnoreCase( inputJson, keyword, deleted, pageable );
        }else{
            return organizationService.getAllOrganizations( deleted, pageable );
        }
    }

    // Applying versioning for the list endpoint.
    @GetMapping( LIST )
    public final ResponseEntity< ? > listOrganizationsv1(){
        return listOrganizations();
    }

    @GetMapping( SLASH + "{organizationId}" )
    public final ResponseEntity< ? > getOrganization( @PathVariable final Long organizationId ){
        Optional< Organization > theOrganization = organizationService.findByOrganizationId( organizationId, false );
        if( theOrganization.isEmpty() ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND )
              .body( "Organization with organization ID " + organizationId + " not found" );
        }
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "Organization Detail retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", theOrganization.get() );
        return ResponseEntity.ok( response );
    }

    @PostMapping( SLASH )
    public final ResponseEntity< ? > addOrganization( @RequestBody final Organization theOrganization ){
        theOrganization.setId( 0L );
        Organization savedOrganization = organizationService.save( theOrganization );
        // Organization savedOrganization = theOrganization;

        if( savedOrganization != null && savedOrganization.getId() != null ){
            Map< String, Object > response = new HashMap<>();
            response.put( "status", "success" );
            response.put( "message", "Organization saved successfully" );
            response.put( "data", savedOrganization );
            return ResponseEntity.ok( response );
        }else{
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to save payment transaction" );
        }
    }

    @PutMapping( SLASH + "{organizationId}" )
    public final Organization updateOrganization( @PathVariable final Long organizationId, @RequestBody final Organization theOrganization ){
        return organizationService.updateOrganization( organizationId, theOrganization );
    }

    @DeleteMapping( SLASH + "{organizationId}" )
    public final ResponseEntity< ? > deleteOrganization( @PathVariable final Long organizationId ){
        Optional< Organization > theOrganization = organizationService.findByOrganizationId( organizationId, false );

        Map< String, Object > response = new HashMap<>();

        if( theOrganization.isEmpty() ){
            throw new NoSuchElementException( "Organization with organization ID " + organizationId + " not found" );
            /*ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("status","error");
            response.put("message", "Organization with organization ID " + organizationId + " not found");*/
        }else{
            organizationService.deleteOrganization( theOrganization.get() );
            response.put( "message", "Organizations and SBUs successfully deleted" );
            response.put( "status", "success" );
            response.put( "data", theOrganization );
        }
        return ResponseEntity.ok( response );
    }

    @PatchMapping( SLASH + "{organizationId}" + SLASH + "{enabled}" )
    public final ResponseEntity< ? > enableOrganization( @PathVariable final Long organizationId,
                                                         @PathVariable final Boolean enabled ){
        Optional< Organization > theOrganization = organizationService.findByOrganizationId( organizationId, false );
        String status = "";
        Map< String, Object > response = new HashMap<>();

        if( theOrganization.isEmpty() ){
            throw new NoSuchElementException( "Organization with organization ID " + organizationId + " not found" );
        }else{
            organizationService.enableOrganization( theOrganization.get(), enabled );
            status = enabled ? "enabled" : "disabled";
            response.put( "message", "Organization and all organization SBUs " + status );
            response.put( "status", "success" );
            // response.put("data", theOrganization);
        }

        return ResponseEntity.ok( response );
    }

}
