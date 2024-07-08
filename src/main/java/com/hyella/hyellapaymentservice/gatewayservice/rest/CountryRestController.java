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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyella.hyellapaymentservice.gatewayservice.entity.Country;
import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;
import com.hyella.hyellapaymentservice.gatewayservice.service.CountryService;


@RestController
@RequestMapping( DEFAULT_VERSION + SLASH + COUNTRY )
public class CountryRestController {
    private CountryService countryService;

    @Autowired
    public CountryRestController( CountryService theCountryService ){
        this.countryService = theCountryService;
    }

    //    Default mapping for an entity returns all objects.
    @RequestMapping( SLASH )
    public List< Country > listCountries(){
        return countryService.findAll();
    }

    //    Applying versioning for the list endpoint.
    @GetMapping( DEFAULT_VERSION + LIST )
    public List< Country > listCountriesv1(){
        return listCountries();
    }

    // @GetMapping(DEFAULT_VERSION + "/{countryCode}")
    // public Country getCountry(@PathVariable String countryCode) {
    //     Optional<Country> theCountry = countryService.findByCountryCode(countryCode);
    //     if (theCountry.isEmpty()) {
    //         throw new NoSuchElementException("Country with country code " + countryCode + " not found");
    //     }
    //     return theCountry.get();
    // }

    @GetMapping( SLASH + "{countryCode}" )
    public final ResponseEntity< ? > getCountry( @PathVariable final String countryCode ){
        Optional< Country > theCountry = countryService.findByCountryCode( countryCode );
        if( theCountry.isEmpty() ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND )
              .body( "Country with Country Code " + countryCode + " not found" );
        }
        Map< String, Object > response = new HashMap<>();
        response.put( "message", "Country Detail retrieved successfully" );
        response.put( "status", "success" );
        response.put( "data", theCountry.get() );
        return ResponseEntity.ok( response );
    }

    @GetMapping( SLASH + "datatable-server" )
    public Page< Country > getCountry(
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "countryName" ) String sortBy, // Default sort by name
      @RequestParam( defaultValue = "asc" ) String sortDirection, // Default sort direction ascending
      @RequestParam( required = false, defaultValue = "false" ) Boolean deleted, // default to false
      @RequestParam( required = false ) String keyword
    ){
        Sort.Direction direction = Sort.Direction.fromString( sortDirection );
        Pageable pageable = PageRequest.of( page, size, direction, sortBy );

        if( keyword != null && !keyword.isEmpty() ){
            return countryService.findByKeywordIgnoreCase( keyword, deleted, pageable );
        }else{
            return countryService.getAllCountry( deleted, pageable );
        }
    }


    @PostMapping( SLASH + "datatable-server" )
    public Page< Country > filterCountry(
      @RequestParam( defaultValue = "0" ) int page, // Default to page 0
      @RequestParam( defaultValue = "10" ) int size, // Default to 10 items per page
      @RequestParam( defaultValue = "countryName" ) String sortBy, // Default sort by name
      @RequestParam( defaultValue = "asc" ) String sortDirection, // Default sort direction ascending
      @RequestParam( required = false, defaultValue = "false" ) Boolean deleted,

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
            return countryService.findByMultipleKeywordsIgnoreCase( inputJson, keyword, deleted, pageable );
        }else{
            return countryService.getAllCountry( deleted, pageable );
        }
    }

    @PostMapping( SLASH )
    public final ResponseEntity< ? > addCountry( @RequestBody final Country theCountry ){
        // theCountry.setId(0L);
        Country savedCountry = countryService.save( theCountry );
        // Organization savedOrganization = theOrganization;

        if( savedCountry != null && savedCountry.getCountryCode() != null ){
            Map< String, Object > response = new HashMap<>();
            response.put( "status", "success" );
            response.put( "message", "Country saved successfully" );
            response.put( "data", savedCountry );
            return ResponseEntity.ok( response );
        }else{
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to save payment transaction" );
        }
    }

    @DeleteMapping( SLASH + "{countryCode}" )
    public final ResponseEntity< ? > deleteCountry( @PathVariable final String countryCode ){
        Optional< Country > theCountry = countryService.findByCountryCode( countryCode );

        Map< String, Object > response = new HashMap<>();

        if( theCountry.isEmpty() ){
            throw new NoSuchElementException( "Organization with organization ID " + countryCode + " not found" );
            /*ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("status","error");
            response.put("message", "Organization with organization ID " + organizationId + " not found");*/
        }else{
            countryService.deleteCountry( theCountry.get() );
            response.put( "message", "Organizations and SBUs successfully deleted" );
            response.put( "status", "success" );
            response.put( "data", theCountry );
        }
        return ResponseEntity.ok( response );
    }


}