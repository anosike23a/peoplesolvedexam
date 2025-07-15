/**
 * 
 */
package com.examtest.worldcountryandcapital;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author anosi
 *
 */
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