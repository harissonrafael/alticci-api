package com.altice.alticci.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.altice.alticci.utils.Constants.*;
import static java.lang.String.format;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Alticci project",
                "Software to calculate alticci sequence.",
                "1.0",
                "Terms of service",
                new Contact("Altice Corporation", "www.example.com", "myeaddress@company.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    private final List<Response> responseMessages = Arrays.asList(
            new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .description("Request Unauthorized")
                    .build(),
            new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.FORBIDDEN.value()))
                    .description("Access is denied")
                    .build()
    );

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.altice.alticci.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo())
                .tags(new Tag(ALTICCI, format(SWAGGER_TAG_DESCRIPTION, ALTICCI_DESCRIPTION)))
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, this.responseMessages);
    }

}
