package com.wagle.backend.common.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI openAPI() {
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme()))
//                .security(Collections.singletonList(securityRequirement))
//                .info(new Info()
//                        .title("Wagle")
//                        .description("API Swagger Docs")
//                        .version("1.0.0")
//                );
//    }
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wagle")
                        .description("API Swagger Docs")
                        .version("1.0.0")
                );
    }

    @Bean
    public GroupedOpenApi userApiGroup() {
        String[] packagesToScan = {"com.wagle.backend.domain.member.controller"};

        return GroupedOpenApi.builder()
                .group("유저")
                .packagesToScan(packagesToScan)
                .build();
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
    }
}
