package com.bpjoshi.tradevalidator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.bpjoshi.fxservice.api")
@ClassInfo(summary="Swagger documentation configuration.")
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/trades"))
                .build()
                .apiInfo(apiInfo());
    }
    

    private ApiInfo apiInfo() {
        String description = "FX Trade Validator API";
        return new ApiInfoBuilder()
                .title("FXTradeValidator")
                .description(description)
                .version("1.0")
                .build();
    }

}
