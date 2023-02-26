package com.example.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 功能描述: swagger接口文档类
 *
 * @author lizongzai
 * @date 2023/02/24 10:15
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

  public Docket createRestApi() {

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
        .paths(PathSelectors.any())
        .build();

  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Restful API Document")
        .description("Restful API Document")
        .contact(new Contact("lizongzai", "http://localhost:8090/doc.html", "lizongzai@gmail.com"))
        .version("1.0")
        .build();
  }

}
