package com.hyella.hyellapaymentservice.gatewayservice.rest;

import static com.hyella.hyellapaymentservice.common.constants.Constants.*;

import java.lang.reflect.Field;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyella.hyellapaymentservice.gatewayservice.entity.BankAccount;
import com.hyella.hyellapaymentservice.gatewayservice.service.BankAccountService;
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


import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnit;
import com.hyella.hyellapaymentservice.gatewayservice.service.StrategicBusinessUnitService;
import com.hyella.hyellapaymentservice.gatewayservice.service.BankAccountService;

@RestController
@RequestMapping( DEFAULT_VERSION + SLASH + STRATEGICBUSINESSUNIT )
public class StrategicBusinessUnitRestController {
    private final StrategicBusinessUnitService strategicbusinessunitService;
    private final BankAccountService bankAccountService;

    @Autowired
    public StrategicBusinessUnitRestController( final StrategicBusinessUnitService theStrategicBusinessUnitService, BankAccountService theBankAccountService ){
        this.strategicbusinessunitService = theStrategicBusinessUnitService;
        this.bankAccountService = theBankAccountService;
    }

    // Default mapping for an entity returns all objects.
    @RequestMapping( SLASH )
    public final ResponseEntity< ? > listStrategicBusinessUnits(){
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "StrategicBusinessUnits retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", strategicbusinessunitService.findAll() );
        return ResponseEntity.ok( response );
    }

    @GetMapping( SLASH + "datatable-server" )
    public Page< StrategicBusinessUnit > getStrategicBusinessUnits(
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
            return strategicbusinessunitService.findByKeywordIgnoreCase( keyword, deleted, pageable );
        }else{
            return strategicbusinessunitService.getAllStrategicBusinessUnits( deleted, pageable );
        }
    }

    @PostMapping( SLASH + "datatable-server" )
    public Page< StrategicBusinessUnit > filterStrategicBusinessUnit(
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
            return strategicbusinessunitService.findByMultipleKeywordsIgnoreCase( inputJson, keyword, deleted, pageable );
        }else{
            return strategicbusinessunitService.getAllStrategicBusinessUnits( deleted, pageable );
        }
    }

    // Applying versioning for the list endpoint.
    @GetMapping( LIST )
    public final ResponseEntity< ? > listStrategicBusinessUnitsv1(){
        return listStrategicBusinessUnits();
    }


    @GetMapping( SLASH + "{strategicbusinessunitId}" )
    public final ResponseEntity< ? > getStrategicBusinessUnit( @PathVariable final Long strategicbusinessunitId ){
        Optional< StrategicBusinessUnit > theStrategicBusinessUnit = strategicbusinessunitService.findByStrategicBusinessUnitId( strategicbusinessunitId, false );
        if( theStrategicBusinessUnit.isEmpty() ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND )
              .body( "StrategicBusinessUnit with strategicbusinessunit ID " + strategicbusinessunitId + " not found" );
        }
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "StrategicBusinessUnit Detail retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", theStrategicBusinessUnit.get() );
        return ResponseEntity.ok( response );
    }

    @GetMapping( SLASH + "get-bank-accounts" + SLASH + "{strategicbusinessunitId}" )
    public final ResponseEntity< ? > getBankAccountForSBU(
      @PathVariable final Long strategicbusinessunitId,
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "name" ) String sortBy, // Default sort by name
      @RequestParam( defaultValue = "asc" ) String sortDirection, // Default sort direction ascending
      @RequestParam( required = false, defaultValue = "false" ) Boolean deleted, // default to false
      @RequestParam( required = false ) String keyword ){
        Sort.Direction direction = Sort.Direction.fromString( sortDirection );
        Pageable pageable = PageRequest.of( page, size, direction, sortBy );
        Page< BankAccount > theStrategicBusinessUnit = bankAccountService.findBySbuId( strategicbusinessunitId, pageable );
        if( theStrategicBusinessUnit.isEmpty() ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND )
              .body( "Bank Account(s) with strategicbusinessunit ID " + strategicbusinessunitId + " not found" );
        }
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "StrategicBusinessUnit Detail retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", theStrategicBusinessUnit.get() );
        return ResponseEntity.ok( response );
    }


    @PostMapping( SLASH )
    public final ResponseEntity< ? > addStrategicBusinessUnit( @RequestBody final StrategicBusinessUnit theStrategicBusinessUnit ){
        theStrategicBusinessUnit.setId( 0L );
        StrategicBusinessUnit savedStrategicBusinessUnit = strategicbusinessunitService.save( theStrategicBusinessUnit );
        // StrategicBusinessUnit savedStrategicBusinessUnit = theStrategicBusinessUnit;

        if( savedStrategicBusinessUnit != null && savedStrategicBusinessUnit.getId() != null ){
            Map< String, Object > response = new HashMap<>();
            response.put( "status", "success" );
            response.put( "message", "StrategicBusinessUnit saved successfully" );
            response.put( "data", savedStrategicBusinessUnit );
            return ResponseEntity.ok( response );
        }else{
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to save payment transaction" );
        }
    }

    @PutMapping( SLASH + "{strategicbusinessunitId}" )
    public final StrategicBusinessUnit updateStrategicBusinessUnit( @PathVariable final Long strategicbusinessunitId, @RequestBody final StrategicBusinessUnit theStrategicBusinessUnit ){
        return strategicbusinessunitService.updateStrategicBusinessUnit( strategicbusinessunitId, theStrategicBusinessUnit );
    }

    @DeleteMapping( SLASH + "{strategicbusinessunitId}" )
    public final ResponseEntity< ? > deleteStrategicBusinessUnit( @PathVariable final Long strategicbusinessunitId ){
        Optional< StrategicBusinessUnit > theStrategicBusinessUnit = strategicbusinessunitService.findByStrategicBusinessUnitId( strategicbusinessunitId, false );

        Map< String, Object > response = new HashMap<>();

        if( theStrategicBusinessUnit.isEmpty() ){
            throw new NoSuchElementException( "StrategicBusinessUnit with strategicbusinessunit ID " + strategicbusinessunitId + " not found" );
            /*ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("status","error");
            response.put("message", "StrategicBusinessUnit with strategicbusinessunit ID " + strategicbusinessunitId + " not found");*/
        }else{
            strategicbusinessunitService.deleteStrategicBusinessUnit( theStrategicBusinessUnit.get() );
            response.put( "message", "StrategicBusinessUnits and SBUs successfully deleted" );
            response.put( "status", "success" );
            response.put( "data", theStrategicBusinessUnit );
        }
        return ResponseEntity.ok( response );
    }

    @PatchMapping( SLASH + "{strategicbusinessunitId}" + SLASH + "{enabled}" )
    public final ResponseEntity< ? > enableStrategicBusinessUnit( @PathVariable final Long strategicbusinessunitId,
                                                                  @PathVariable final Boolean enabled ){
        Optional< StrategicBusinessUnit > theStrategicBusinessUnit = strategicbusinessunitService.findByStrategicBusinessUnitId( strategicbusinessunitId, false );
        String status = "";
        Map< String, Object > response = new HashMap<>();

        if( theStrategicBusinessUnit.isEmpty() ){
            throw new NoSuchElementException( "StrategicBusinessUnit with strategicbusinessunit ID " + strategicbusinessunitId + " not found" );
        }else{
            strategicbusinessunitService.enableStrategicBusinessUnit( theStrategicBusinessUnit.get(), enabled );
            status = enabled ? "enabled" : "disabled";
            response.put( "message", "StrategicBusinessUnit and all strategicbusinessunit SBUs " + status );
            response.put( "status", "success" );
            // response.put("data", theStrategicBusinessUnit);
        }

        return ResponseEntity.ok( response );
    }

}
