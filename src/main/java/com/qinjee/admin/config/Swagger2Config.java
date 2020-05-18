/*
 * 文件名： Swagger2.java
 *
 * 工程名称: qinjee-tsc
 *
 * Qinjee
 *
 * 创建日期： 2019年6月3日
 *
 * Copyright(C) 2019, by zhouyun
 *
 * 原始作者: 周赟
 *
 */
package com.qinjee.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
public class Swagger2Config {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qinjee.admin.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "DHR客户管理后台接口文档",
                "管理员：penghs ",
                "v1.0.0",
                "http://localhost:8080/swagger-ui.html#/",
                new Contact("penghs","https://","penghs@qinjee.cn"),
                "勤杰官网",
                "http://www.qinjee.cn/",
                Collections.emptyList()
        );
    }
}
