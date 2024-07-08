# Data Manager Service
- It's used to provide backend CRUD operations for powering datatables
- Adopted for d1kit java implementation and repurposed for HPS
- Paginated, Searchable & Filterable results
- Mutated POST request used for advance search filtering (not-fully tested & implemented)
- [Sample Endpoints](https://restless-sunset-44843.postman.co/workspace/Restless-Sunset-Workspace~84240ca5-6d08-45a5-bb1c-94908d007a22/folder/31925882-2e76b6fa-6469-4fb9-8262-1b488612c5e8?ctx=documentation)

## Code Flow
REST API Call -> Controller(rest) -> Service (Interface & Implementation) -> Repository/Data Access Object (DAO) -> Model(Entity) -> DB   
- filter: define fields in service implementation. e.g: OrgainzationServiceImpl
```dockerfile
#sample filter code
return criteriaBuilder
.and(
    criteriaBuilder.equal(root.get("deleted"), deleted)
    ,
    criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("phoneNumber")), pattern),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("emailAddress")), pattern),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), pattern)
    )
);

#or

CriteriaBuilder cb = criteriaBuilder;
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
return (Predicate) cb;
```
- 

## Set-up for Dev
1. Clone data-manager-service repo
`git clone https://github.com/Hyella-Limited/hyella-data-manager-service.git`

2. Ensure pom.xml file contains all relevant names
```
search and replace "data-manager-service" with "your-serice-name"
replace description "Data Manager Service to provide backend CRUD ..." with "What-your-service-does"

comment out this dependenceis     
<!-- <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency> -->
<!-- <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency> -->
```

3. Goto your ***.m2/*** directory in your PC and ensure it has relevant github settings,   
 - How to Locate .m2 directory 
 ```
   linux: /home/username/.m2   
   windows: c:\Users\username\.m2
 ```   
   It's a hidden folder, so manipulate your way around that constraint
 
- Create a settings.xml file in the .m2 directory, if none exists  
- Populate the settings.xml file with the below info
    ```
    <settings>
        <servers>
            <server>
                <id>github-repo</id>
                <username>username</username>
                <password>ghp_TOKEN</password>
            </server>
        </servers>
    </settings>
    ```
- Make sure to replace the placeholders with your own username and token    

4. Connect to your local or cloud db by updating values in hyella-data-manager-service/src/main/resources/application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/DB_NAME
spring.jpa.properties.hibernate.default_schema=DB_SCHEMA

spring.datasource.username=hps_XXX
spring.datasource.password=XXXXX

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=none
```

5. Ensure you have at least java 17 or above installed, verify this by running   
`java --version`

6. On IntelliJ / VScode open your terminal
7. Connect to the internet
8. [Install Maven on your PC](./install-mvn.md)
7. Run the below commands in terminal to test the set-up
```
#springboot clean (major or minor)
mvn clean install -U    #major cleaning
mvn clean               #minor

mvn spring-boot:run
```
