# peoplesolvedexam
peoplesolved.com exam Test
Add dependency: springdoc-openapi-starter-webmvc-ui
Visit /swagger-ui.html or /swagger-ui/index.html
country-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/country/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── CountryApiApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/flags/  ← for storing uploaded country flag images
│   └── test/java/com/example/country/
│       ├── controller/
│       ├── service/
│       └── repository/
├── Dockerfile
└── .github/workflows/ci-cd.yml

Dockerfile
-------------
FROM openjdk:17
VOLUME /tmp
COPY target/country-api.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

CI/CD Pipeline (GitHub Actions YAML)
--------------------------------------
name: CI/CD Pipeline

on:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Build with Maven
        run: mvn clean install
      - name: Run Tests
        run: mvn test



write unit Tests 
--------------------
Unit Tests (Junit + Mockito)
CountryServiceTest
CountryControllerTest
CountryRepositoryTest


Postman Collection API to be tested  for crud operations
-------------------------------------------------------------
I'll generate a .json collection file for testing the following endpoints:

GET /countries

GET /countries/{name}

GET /countries/population/{population}

GET /countries/capital/{capital}

POST /countries (form-data with image and JSON)

Database used : Mysql
---------------------
create a database on mysql client with the following query 
create database countrydb;

now run the program to auto generate tables in the database.

write a query to show all the tables inthe database
show tables;
desc table name

here is Here’s how to integrate Swagger (OpenAPI 3) in Spring Boot 3 with springdoc-openapi, and expose the OpenAPI docs UI endpoint.
-------------------------------------------------------------------------------------------------------------------------------------------

Step 1: Add Swagger dependency to pom.xml
---------------------------------------------
add this dependency to pom.xml

<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.3.0</version>
</dependency>

Step 2: Basic Configuration (optional)
----------------------------------------
Springdoc automatically scans your REST controllers and generates docs. You can customize via properties in application.yml:

springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html

    
Step 3: Access Swagger UI
-----------------------------
Run your Spring Boot app and visit:

http://localhost:8080/swagger-ui.html
or
http://localhost:8080/swagger-ui/index.html
This loads the interactive Swagger UI where you can test all your REST endpoints.

Step 4: Add OpenAPI info metadata (Optional but recommended)
Create a config class to customize API title, version, description:

create a config file
---------------------------

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Country API")
                .version("1.0.0")
                .description("REST API for managing countries"));
    }
}


Step 5: Annotate your Controllers or DTOs (optional for richer docs)
--------------------------------------------------------------------------
You can add Swagger annotations to your controller methods or DTOs for better documentation:


@Operation(summary = "Retrieve all countries")
@GetMapping("/countries")
public List<CountryResponseDTO> getAllCountries() { ... }
Summary
Add dependency: springdoc-openapi-starter-webmvc-ui

Visit /swagger-ui.html or /swagger-ui/index.html

Optional: Add OpenAPI bean config for metadata

Optional: Add annotations for detailed API docs



frontend code design 
-------------------------
src/app/
│
├── core/
│   ├── services/
│   │   └── country.service.ts
│   └── models/
│       └── country.model.ts
│
├── pages/
│   ├── home/
│   │   └── home.component.{ts,html,scss}
│   ├── country-detail/
│   │   └── country-detail.component.{ts,html,scss}
│   ├── search/
│       └── search.component.{ts,html,scss}
│
├── shared/
│   └── country-card/
│       └── country-card.component.{ts,html,scss}
│
└── app.routes.ts


