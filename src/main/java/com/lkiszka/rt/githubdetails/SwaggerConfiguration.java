/*
 * Copyright (c) 2018 Sonalake. All rights reserved.
 *
 */

package com.lkiszka.rt.githubdetails;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Lukasz Kiszka
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${swagger.enabled:false}")
    private boolean swaggerEnabled;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Github details repository")
                .description("Service for get information about some github repository by repository owner and repository name")
                .version(GithubDetailsApplication.API_VERSION)
                .build();
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lkiszka.rt.githubdetails.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled);
    }
}
