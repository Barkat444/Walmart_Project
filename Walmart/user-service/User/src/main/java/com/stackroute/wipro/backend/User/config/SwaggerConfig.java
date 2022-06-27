package com.stackroute.wipro.backend.User.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKeys()
    {
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }
    //springfox.documentation.spi.service.contexts.SecurityContext
    private List<SecurityContext> securityContext()
    {
        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
    }

    private List<SecurityReference> sf()
    {
        AuthorizationScope scopes = new AuthorizationScope("global","accessEverthing");
        return Arrays.asList(new SecurityReference("scope",new AuthorizationScope[]{ scopes }));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContext())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();

    }

    private ApiInfo getInfo() {
        return new ApiInfo("Walmart User Documentation", "Project Developed by Azhar,Barkat and Prashsnt","1.0","null",new Contact("Wipro-Velocity Stackroute Batch 9 Team-2","null","azhar91099@gmail.com"),".license(\"Apache 2.0\")","Api Url", Collections.emptyList());
    }


}