package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket proudapi()
    {
        return new
                Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(regex("/menu.*"))
                        .build()
                .apiInfo(metaInfo());

    }


    private ApiInfo metaInfo() {
        ApiInfo apiInfo=new ApiInfo(
                "MENU CARD",
                "RESTAURANT ITEMS",
                "1.0.1",
                "terms of service",
                new Contact("sharma","https://www.youtube.com",
                        "http:google.com"),
                "Book Table","http:gmail.com"
        );
        return apiInfo;


    }


}
