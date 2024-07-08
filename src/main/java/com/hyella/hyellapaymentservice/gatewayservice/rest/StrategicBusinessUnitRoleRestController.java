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

import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnitRole;
import com.hyella.hyellapaymentservice.gatewayservice.service.StrategicBusinessUnitRoleService;

@RestController
@RequestMapping( DEFAULT_VERSION + SLASH + STRATEGICBUSINESSUNITROLE )
public class StrategicBusinessUnitRoleRestController {
    private final StrategicBusinessUnitRoleService strategicbusinessunitroleService;

    @Autowired
    public StrategicBusinessUnitRoleRestController( final StrategicBusinessUnitRoleService theStrategicBusinessUnitRoleService ){
        this.strategicbusinessunitroleService = theStrategicBusinessUnitRoleService;
    }

    // Default mapping for an entity returns all objects.
    @RequestMapping( SLASH )
    public final ResponseEntity< ? > listStrategicBusinessUnitRoles(){
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "StrategicBusinessUnitRoles retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", strategicbusinessunitroleService.findAll() );
        return ResponseEntity.ok( response );
    }

    @GetMapping( SLASH + "datatable-server" )
    public Page< StrategicBusinessUnitRole > getStrategicBusinessUnitRoles(
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "role_id" ) String sortBy, // Default sort by name
      @RequestParam( defaultValue = "asc" ) String sortDirection, // Default sort direction ascending
      @RequestParam( required = false, defaultValue = "false" ) Boolean deleted, // default to false
      @RequestParam( required = false ) String keyword
    ){
        Sort.Direction direction = Sort.Direction.fromString( sortDirection );
        Pageable pageable = PageRequest.of( page, size, direction, sortBy );

        if( keyword != null && !keyword.isEmpty() ){
            return strategicbusinessunitroleService.findByKeywordIgnoreCase( keyword, deleted, pageable );
        }else{
            return strategicbusinessunitroleService.getAllStrategicBusinessUnitRoles( deleted, pageable );
        }
    }

    @PostMapping( SLASH + "datatable-server" )
    public Page< StrategicBusinessUnitRole > filterStrategicBusinessUnitRole(
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "role_id" ) String sortBy, // Default sort by name
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
            return strategicbusinessunitroleService.findByMultipleKeywordsIgnoreCase( inputJson, keyword, deleted, pageable );
        }else{
            return strategicbusinessunitroleService.getAllStrategicBusinessUnitRoles( deleted, pageable );
        }
    }

    // Applying versioning for the list endpoint.
    @GetMapping( LIST )
    public final ResponseEntity< ? > listStrategicBusinessUnitRolesv1(){
        return listStrategicBusinessUnitRoles();
    }

    @GetMapping( SLASH + "{strategicbusinessunitroleId}" )
    public final ResponseEntity< ? > getStrategicBusinessUnitRole( @PathVariable final Long strategicbusinessunitroleId ){
        Optional< StrategicBusinessUnitRole > theStrategicBusinessUnitRole = strategicbusinessunitroleService.findByStrategicBusinessUnitRoleId( strategicbusinessunitroleId, false );
        if( theStrategicBusinessUnitRole.isEmpty() ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND )
              .body( "StrategicBusinessUnitRole with strategicbusinessunitrole ID " + strategicbusinessunitroleId + " not found" );
        }
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "StrategicBusinessUnitRole Detail retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", theStrategicBusinessUnitRole.get() );
        return ResponseEntity.ok( response );
    }

    @PostMapping( SLASH )
    public final ResponseEntity< ? > addStrategicBusinessUnitRole( @RequestBody final StrategicBusinessUnitRole theStrategicBusinessUnitRole ){
        theStrategicBusinessUnitRole.setId( 0L );
        StrategicBusinessUnitRole savedStrategicBusinessUnitRole = strategicbusinessunitroleService.save( theStrategicBusinessUnitRole );
        // StrategicBusinessUnitRole savedStrategicBusinessUnitRole = theStrategicBusinessUnitRole;

        if( savedStrategicBusinessUnitRole != null && savedStrategicBusinessUnitRole.getId() != null ){
            Map< String, Object > response = new HashMap<>();
            response.put( "status", "success" );
            response.put( "message", "StrategicBusinessUnitRole saved successfully" );
            response.put( "data", savedStrategicBusinessUnitRole );
            return ResponseEntity.ok( response );
        }else{
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to save payment transaction" );
        }
    }

    @PutMapping( SLASH + "{strategicbusinessunitroleId}" )
    public final StrategicBusinessUnitRole updateStrategicBusinessUnitRole( @PathVariable final Long strategicbusinessunitroleId, @RequestBody final StrategicBusinessUnitRole theStrategicBusinessUnitRole ){
        return strategicbusinessunitroleService.updateStrategicBusinessUnitRole( strategicbusinessunitroleId, theStrategicBusinessUnitRole );
    }

    @DeleteMapping( SLASH + "{strategicbusinessunitroleId}" )
    public final ResponseEntity< ? > deleteStrategicBusinessUnitRole( @PathVariable final Long strategicbusinessunitroleId ){
        Optional< StrategicBusinessUnitRole > theStrategicBusinessUnitRole = strategicbusinessunitroleService.findByStrategicBusinessUnitRoleId( strategicbusinessunitroleId, false );

        Map< String, Object > response = new HashMap<>();

        if( theStrategicBusinessUnitRole.isEmpty() ){
            throw new NoSuchElementException( "StrategicBusinessUnitRole with strategicbusinessunitrole ID " + strategicbusinessunitroleId + " not found" );
            /*ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("status","error");
            response.put("message", "StrategicBusinessUnitRole with strategicbusinessunitrole ID " + strategicbusinessunitroleId + " not found");*/
        }else{
            strategicbusinessunitroleService.deleteStrategicBusinessUnitRole( theStrategicBusinessUnitRole.get() );
            response.put( "message", "StrategicBusinessUnitRoles and SBUs successfully deleted" );
            response.put( "status", "success" );
            response.put( "data", theStrategicBusinessUnitRole );
        }
        return ResponseEntity.ok( response );
    }

    @PatchMapping( SLASH + "{strategicbusinessunitroleId}" + SLASH + "{enabled}" )
    public final ResponseEntity< ? > enableStrategicBusinessUnitRole( @PathVariable final Long strategicbusinessunitroleId,
                                                                      @PathVariable final Boolean enabled ){
        Optional< StrategicBusinessUnitRole > theStrategicBusinessUnitRole = strategicbusinessunitroleService.findByStrategicBusinessUnitRoleId( strategicbusinessunitroleId, false );
        String status = "";
        Map< String, Object > response = new HashMap<>();

        if( theStrategicBusinessUnitRole.isEmpty() ){
            throw new NoSuchElementException( "StrategicBusinessUnitRole with strategicbusinessunitrole ID " + strategicbusinessunitroleId + " not found" );
        }else{
            strategicbusinessunitroleService.enableStrategicBusinessUnitRole( theStrategicBusinessUnitRole.get(), enabled );
            status = enabled ? "enabled" : "disabled";
            response.put( "message", "StrategicBusinessUnitRole and all strategicbusinessunitrole SBUs " + status );
            response.put( "status", "success" );
            // response.put("data", theStrategicBusinessUnitRole);
        }

        return ResponseEntity.ok( response );
    }

}
