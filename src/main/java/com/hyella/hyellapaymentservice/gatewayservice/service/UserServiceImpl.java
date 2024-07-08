package com.hyella.hyellapaymentservice.gatewayservice.service;

import java.lang.reflect.Field;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

import com.hyella.hyellapaymentservice.gatewayservice.entity.User;
import com.hyella.hyellapaymentservice.gatewayservice.entity.StrategicBusinessUnit;

import com.hyella.hyellapaymentservice.gatewayservice.dao.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final StrategicBusinessUnitService strategicBusinessUnitService;

    @Autowired
    public UserServiceImpl( UserRepository theUserRepository, StrategicBusinessUnitService sbuService ){
        this.userRepository = theUserRepository;
        this.strategicBusinessUnitService = sbuService;
    }

    public Page< User > getAllUsers( Boolean deleted, Pageable pageable ){
        return userRepository.findByDeleted( deleted, pageable );
        // return userRepository.findAll(pageable);
    }

    @Override
    public Page< User > getUsersByNameContaining( String name, Pageable pageable ){
        return userRepository.findByNameContaining( name, pageable );
    }


    @Override
    public Page< User > findByKeywordIgnoreCase( String keyword, Boolean deleted, Pageable pageable ){
        Specification< User > spec = keywordSearch( keyword, deleted, null );
        return userRepository.findAll( spec, pageable );
    }

    @Override
    public Page< User > findByMultipleKeywordsIgnoreCase( Map< String, Object > inputJson, String keyword, Boolean deleted, Pageable pageable ){
        Specification< User > spec = keywordSearch( keyword, deleted, inputJson );
        return userRepository.findAll( spec, pageable );
    }

    private Specification< User > keywordSearch( String keyword,
                                                 Boolean deleted,
                                                 Map< String, Object > inputJson ){
        return ( root, query, criteriaBuilder ) -> {
            Integer searchType = 0;

            String pattern = "";
            CriteriaBuilder cb = criteriaBuilder;
            List< Predicate > andPredicates = new ArrayList<>();
            List< Predicate > orPredicates = new ArrayList<>();
            List< Predicate > multiPredicates = new ArrayList<>();
            List< Predicate > multiOrPredicates = new ArrayList<>();
            List< Predicate > multiAndPredicates = new ArrayList<>();

            Field[] fields = User.class.getDeclaredFields();

            if( inputJson != null ){
                // Combined Quick & Multi-Field Specific Search
                // e.g ( (Deleted) and (MultiSearch) and (Quick Search) )
                // MultiSearch = ( (age between 1 and 5) and (country=15) ) or ( (email Like %@gmail.com%) )

                List< String > fieldNames = getFieldNames( fields );

                JSONObject jsonObject = new JSONObject( inputJson );
                Map< String, List< Predicate > > queryPredicates = new HashMap<>();

                try{
                    int len = jsonObject.names().length();
                    LinkedHashSet< String > djKeys = new LinkedHashSet<>( jsonObject.keySet() );
                    JSONArray nodeKeys = new JSONArray();
                    for( String dKey : djKeys ){
                        nodeKeys.put( dKey );
                    }
                    System.out.println( "skeys" );
                    System.out.println( nodeKeys );
                    for( int i = 0; i < len; i++ ){
                        String key = jsonObject.names().getString( i );
                        JSONObject queryNode = jsonObject.getJSONObject( key );

                        // JsonNode queryNode = mapper.valueToTree(value);

                        Map< String, List< Predicate > > nodePredicates = new HashMap<>();

                        String queryCondition = queryNode.get( "condition" ).toString().toLowerCase();
                        // Check if there's a next entry
                        if( queryCondition.isEmpty() && i + 1 < len ){
                            String keyNext = nodeKeys.getString( i + 1 );
                            queryCondition = jsonObject.getJSONObject( keyNext ).getString( "condition" ).toLowerCase();
                        }

                        // Extract search criteria for each query condition
                        JSONObject dataNode = queryNode.getJSONObject( "data" );

                        LinkedHashSet< String > dKeys = new LinkedHashSet<>( dataNode.keySet() );
                        JSONArray dataNodeKeys = new JSONArray();
                        for( String dKey : dKeys ){
                            dataNodeKeys.put( dKey );
                        }
                        int lenChild = dataNode.names().length();
                        for( int iChild = 0; iChild < lenChild; iChild++ ){
                            // String keyChild = dataNode.names().getString(iChild);
                            String keyChild = dataNodeKeys.getString( iChild );
                            JSONObject searchDataNode = dataNode.getJSONObject( keyChild );

                            String field = searchDataNode.getString( "field" );
                            // validate field names
                            if( fieldNames.contains( field ) ){
                                String condition = searchDataNode.getString( "condition" ).toLowerCase();
                                String searchValue = searchDataNode.getString( "search_value" );

                                String logicalOperator = searchDataNode.getString( "logical_operator" ).toLowerCase();
                                if( logicalOperator.isEmpty() && iChild + 1 < lenChild ){
                                    String keyNext = dataNodeKeys.getString( iChild + 1 );
                                    logicalOperator = dataNode.getJSONObject( keyNext ).getString( "logical_operator" ).toLowerCase();
                                }

                                if( !logicalOperator.equals( "or" ) ){
                                    logicalOperator = "and";
                                }

                                // Retrieve the list of predicates associated with the "and" key
                                List< Predicate > tmpPredicates = nodePredicates.get( logicalOperator );
                                if( tmpPredicates == null ){
                                    tmpPredicates = new ArrayList<>();
                                }

                                if( condition.equals( "equal_to" ) ){
                                    tmpPredicates.add( cb.equal( cb.lower( root.get( field ) ), searchValue ) );
                                }else if( condition.equals( "not_equal_to" ) ){
                                    tmpPredicates.add( cb.notEqual( cb.lower( root.get( field ) ), searchValue ) );
                                }else if( condition.equals( "contains" ) ){
                                    tmpPredicates.add( cb.like( cb.lower( root.get( field ) ), "%" + searchValue.toLowerCase() + "%" ) );
                                }else if( condition.equals( "in" ) || condition.equals( "not_in" ) ){
                                    CriteriaBuilder.In< String > InClause = cb.in( cb.lower( root.get( field ) ) );

                                    String[] inValues = searchValue.split( "," );
                                    for( String inValue : inValues ){
                                        InClause.value( inValue );
                                    }
                                    tmpPredicates.add( InClause );
                                }else if( condition.equals( "between" ) ){
                                    if( searchDataNode.has( "min" ) && searchDataNode.has( "max" ) ){
                                        tmpPredicates.add(
                                          cb.between( root.get( field ),
                                            Double.parseDouble( searchDataNode.get( "min" ).toString() ),
                                            Double.parseDouble( searchDataNode.get( "max" ).toString() ) )
                                        );
                                    }
                                }

                                nodePredicates.put( logicalOperator, tmpPredicates );

                                // Process the extracted search criteria as needed
                                // System.out.println("Field: " + field + ", Condition: " + condition + ", Search Value: " + searchValue + ", operator: " + logicalOperator);

                            }else{
                                // System.out.println("Invalid Field: " + field);
                            }
                        }

                        List< Predicate > tmpPredicates = queryPredicates.get( queryCondition );
                        if( tmpPredicates == null ){
                            tmpPredicates = new ArrayList<>();
                        }
                        System.out.println( "Node" + key );
                        System.out.println( queryCondition );
                        if( queryCondition.equals( "or" ) ){
                            multiOrPredicates.add( cb.or( getPredicates( new ArrayList<>(), nodePredicates, cb ).toArray( new Predicate[ 0 ] ) ) );
                        }else{
                            multiAndPredicates.add( cb.and( getPredicates( new ArrayList<>(), nodePredicates, cb ).toArray( new Predicate[ 0 ] ) ) );
                        }

                    }
                }catch( JSONException e ){
                    throw new RuntimeException( e );
                }

                if( !multiOrPredicates.isEmpty() ){
                    multiPredicates.add( cb.or( multiOrPredicates.toArray( new Predicate[ 0 ] ) ) );
                }
                if( !multiAndPredicates.isEmpty() ){
                    multiPredicates.add( cb.and( multiAndPredicates.toArray( new Predicate[ 0 ] ) ) );
                }
                if( !multiPredicates.isEmpty() ){
                    andPredicates.add( cb.and( multiPredicates.toArray( new Predicate[ 0 ] ) ) );
                }
            }

            if( !( keyword == null || keyword.isEmpty() ) ){
                searchType = 1;     // single keyword
            }

            andPredicates.add( cb.equal( root.get( "deleted" ), deleted ) ); // Add deleted condition


            if( searchType == 1 ){
                // Quick Search: searches only fields of the type string
                // e.g (name Like %bola%) or (address like %bola%) or (email Like %bola%)

                pattern = "%" + keyword.toLowerCase() + "%";

                for( Field field : fields ){
                    if( field.getType() == String.class ){
                        orPredicates.add( cb.like( cb.lower( root.get( field.getName() ) ), pattern ) );
                    }
                }
                andPredicates.add( cb.or( orPredicates.toArray( new Predicate[ 0 ] ) ) ); // Combine field andPredicates with OR

                /*return criteriaBuilder
                    .and(
                        criteriaBuilder.equal(root.get("deleted"), deleted)
                        ,
                        criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("phoneNumber")), pattern),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("emailAddress")), pattern),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), pattern)
                        )
                    );*/
            }else if( searchType == 2 ){

            }else if( searchType == 3 ){
                // advance search: not yet coded
                andPredicates.add( cb.equal( root.get( "deleted" ), deleted ) ); // Add deleted condition

                // Add all fields to search
                List< String > fieldsToSearch = List.of( "name", "phoneNumber", "emailAddress" );
                for( String field : fieldsToSearch ){
                    orPredicates.add( cb.like( cb.lower( root.get( field ) ), pattern ) );
                }

                andPredicates.add( cb.or( orPredicates.toArray( new Predicate[ 0 ] ) ) ); // Combine field andPredicates with OR

                /*CriteriaBuilder cb = criteriaBuilder;
                cb.and(
                    cb.equal(root.get("deleted"), deleted)
                    ,
                    cb.or(
                        cb.like(cb.lower(root.get("name")), pattern),
                        cb.like(cb.lower(root.get("phoneNumber")), pattern),
                        cb.like(cb.lower(root.get("emailAddress")), pattern),
                        cb.like(cb.lower(root.get("address")), pattern)
                    )
                );
                return (Predicate) cb;*/
            }

            // Combine all predicates with AND
            return cb.and( andPredicates.toArray( new Predicate[ 0 ] ) );
        };
    }

    private List< Predicate > getPredicates( List< Predicate > previousPredicates, Map< String, List< Predicate > > nodePredicates, CriteriaBuilder cb ){
        List< Predicate > predicates = previousPredicates;
        for( Map.Entry< String, List< Predicate > > nodeEntry : nodePredicates.entrySet() ){
            String logicalOperator = nodeEntry.getKey();
            List< Predicate > tmpPredicates = nodeEntry.getValue();

            if( "and".equals( logicalOperator ) ){
                Predicate andPredicate = cb.and( tmpPredicates.toArray( new Predicate[ 0 ] ) );
                predicates.add( andPredicate );
            }else if( "or".equals( logicalOperator ) ){
                Predicate orPredicate = cb.or( tmpPredicates.toArray( new Predicate[ 0 ] ) );
                predicates.add( orPredicate );
            }
        }
        return predicates;
    }

    private List< String > getFieldNames( Field[] fields ){
        List< String > fieldNames = new ArrayList<>();
        for( Field field : fields ){
            fieldNames.add( field.getName() );
        }
        return fieldNames;
    }

    @Override
    public User updateUser( Long userId, User theUser ){
        User existingEntity = userRepository.findById( userId ).orElse( null );
        if( existingEntity != null ){
            // Get all fields of the entity class
            Field[] fields = User.class.getDeclaredFields();
            for( Field field : fields ){
                field.setAccessible( true ); // Allow access to private fields
                try{
                    // Get the value of the field in the updated entity
                    Object updatedValue = field.get( theUser );
                    if( updatedValue != null ){
                        // Update the corresponding field in the existing entity
                        field.set( existingEntity, updatedValue );
                    }
                }catch( IllegalAccessException e ){
                    e.printStackTrace(); // Handle the exception as needed
                }
            }
            // Save the updated entity
            if( existingEntity != null ){
                return userRepository.save( existingEntity );
            }
            return null;
        }else{
            // Handle the case where the entity with the given ID is not found
            return null;
        }
    }

    @Override
    public List< User > findAll(){
        return userRepository.findAllByOrderByNameAsc();
    }

    // @Override
    // public Optional<User> findByUserId(Long userId, Boolean deleted) {
    //     return userRepository.findByIdAndDeleted(userId, deleted);
    // }

    @Override
    public Optional< User > findByUserId( Long userId, Boolean deleted ){
        return userRepository.findById( userId );
    }

    @Override
    @Transactional
    public User save( User theUser ){
        return userRepository.save( theUser );
    }

    @Override
    @Transactional
    public void deleteUser( User theUser ){
        // List<StrategicBusinessUnit> orgSBUs = strategicBusinessUnitService.getAllSBUsForUser(theUser.getId());
        // for(StrategicBusinessUnit sbu : orgSBUs){
        //     strategicBusinessUnitService.enableSBU(sbu, false);
        //     strategicBusinessUnitService.deleteSBU(sbu);
        // }
        theUser.setDeleted( true );
        this.save( theUser );
    }


    // @Transactional
    // @Override
    // public void enableUser(User theUser, boolean enabled){
    //     List<StrategicBusinessUnit> orgSBUs = strategicBusinessUnitService.getAllSBUsForUser(theUser.getId());
    //     for(StrategicBusinessUnit sbu : orgSBUs){
    //         strategicBusinessUnitService.enableSBU(sbu, enabled);
    //     }
    //     theUser.setEnabled(enabled);
    //     this.save(theUser);
    // }

}
