package com.hyella.hyellapaymentservice.gatewayservice.rest;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Role;
import com.hyella.hyellapaymentservice.gatewayservice.entity.Role;
import com.hyella.hyellapaymentservice.gatewayservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.hyella.hyellapaymentservice.common.constants.Constants.*;

@RestController
@RequestMapping( DEFAULT_VERSION + SLASH + ROLE )
public class RoleRestController {
    private final RoleService roleService;

    @Autowired
    public RoleRestController( final RoleService theRoleService ){
        this.roleService = theRoleService;
    }

    // Default mapping for an entity returns all objects.
    @RequestMapping( SLASH )
    public final ResponseEntity< ? > listRoles(){
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "Roles retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", roleService.findAll() );
        return ResponseEntity.ok( response );
    }

    @GetMapping( SLASH + "datatable-server" )
    public Page< Role > getRoles(
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "name" ) String sortBy, // Default sort by name
      @RequestParam( defaultValue = "asc" ) String sortDirection, // Default sort direction ascending
      @RequestParam( required = false, defaultValue = "false" ) Boolean deleted, // default to false
      @RequestParam( required = false ) String keyword ){
        Sort.Direction direction = Sort.Direction.fromString( sortDirection );
        Pageable pageable = PageRequest.of( page, size, direction, sortBy );

        if( keyword != null && !keyword.isEmpty() ){
            return roleService.findByKeywordIgnoreCase( keyword, deleted, pageable );
        }else{
            return roleService.getAllRoles( deleted, pageable );
        }
    }

    @PostMapping( SLASH + "datatable-server" )
    public Page< Role > filterRole(
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
            return roleService.findByMultipleKeywordsIgnoreCase( inputJson, keyword, deleted, pageable );
        }else{
            return roleService.getAllRoles( deleted, pageable );
        }
    }

    // Applying versioning for the list endpoint.
    @GetMapping( LIST )
    public final ResponseEntity< ? > listRolesv1(){
        return listRoles();
    }

    @GetMapping( SLASH + "{roleId}" )
    public final ResponseEntity< ? > getRole( @PathVariable final Long roleId ){
        Optional< Role > theRole = roleService.findByRoleId( roleId, false );
        if( theRole.isEmpty() ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND )
              .body( "Role with role ID " + roleId + " not found" );
        }
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "Role Detail retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", theRole.get() );
        return ResponseEntity.ok( response );
    }

    @PostMapping( SLASH )
    public final ResponseEntity< ? > addRole( @RequestBody final Role theRole ){
        theRole.setId( 0L );
        Role savedRole = roleService.save( theRole );
        // Organization savedOrganization = theOrganization;

        if( savedRole != null && savedRole.getId() != null ){
            Map< String, Object > response = new HashMap<>();
            response.put( "status", "success" );
            response.put( "message", "Role saved successfully" );
            response.put( "data", savedRole );
            return ResponseEntity.ok( response );
        }else{
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to save payment transaction" );
        }
    }

    @DeleteMapping( SLASH + "{roleId}" )
    public final ResponseEntity< ? > deleteRole( @PathVariable final Long roleId ){
        Optional< Role > theRole = roleService.findByRoleId( roleId, false );

        Map< String, Object > response = new HashMap<>();

        if( theRole.isEmpty() ){
            throw new NoSuchElementException( "Role with role ID " + roleId + " not found" );
            /*ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("status","error");
            response.put("message", "Organization with organization ID " + organizationId + " not found");*/
        }else{
            roleService.deleteRole( theRole.get() );
            response.put( "message", "Role successfully deleted" );
            response.put( "status", "success" );
            response.put( "data", theRole );
        }
        return ResponseEntity.ok( response );
    }

    @PatchMapping( SLASH + "{roleId}" + SLASH + "{enabled}" )
    public final ResponseEntity< ? > enableRole( @PathVariable final Long roleId,
                                                 @PathVariable final Boolean enabled ){
        Optional< Role > theRole = roleService.findByRoleId( roleId, false );
        String status = "";
        Map< String, Object > response = new HashMap<>();

        if( theRole.isEmpty() ){
            throw new NoSuchElementException( "Role with role ID " + roleId + " not found" );
        }else{
            roleService.enableRole( theRole.get(), enabled );
            status = enabled ? "enabled" : "disabled";
            response.put( "message", "Role has been " + status );
            response.put( "status", "success" );
            // response.put("data", theOrganization);
        }

        return ResponseEntity.ok( response );
    }

    @PutMapping( SLASH + "{roleId}" )
    public final Role updateRole( @PathVariable final Long roleId, @RequestBody final Role theRole ){
        return roleService.updateRole( roleId, theRole );
    }


}
