package com.hyella.hyellapaymentservice.gatewayservice.config;

import java.util.HashMap;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter( autoApply = true )
public class HashMapConverter implements AttributeConverter< HashMap< ?, ? >, String > {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn( HashMap< ?, ? > attribute ){
        if( attribute == null ){
            return null;
        }
        try{
            return objectMapper.writeValueAsString( attribute );
        }catch( Exception e ){
            throw new RuntimeException( "Error converting HashMap to JSON string: " + e.getMessage() );
        }
    }

    @Override
    public HashMap< ?, ? > convertToEntityAttribute( String dbData ){
        if( dbData == null ){
            return null;
        }
        try{
            return objectMapper.readValue( dbData, HashMap.class );
        }catch( Exception e ){
            throw new RuntimeException( "Error converting JSON string to HashMap: " + e.getMessage() );
        }
    }
}
