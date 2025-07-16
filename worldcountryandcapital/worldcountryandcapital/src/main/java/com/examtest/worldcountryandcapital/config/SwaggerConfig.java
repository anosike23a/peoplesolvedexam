/**
 * 
 */
package com.examtest.worldcountryandcapital.config;

/**
 * @author anosi
 *
 */

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI countriesOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Countries API")
                        .description("API for managing countries and their capitals")
                        .version("1.0"));
    }
}
