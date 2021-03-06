package com.dood.tdd.kotlinmicroservice.users.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

@Configuration
@EnableSwagger2WebFlux
@ComponentScan("com.dood.tdd.kotlinmicroservice.users.web")
class SwaggerConfig {
//    @Bean
//    fun api(): Docket {
//        return Docket(DocumentationType.SWAGGER_2)
//                .select()
////                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.dood.tdd.kotlinmicroservice.users.web"))
//
//                .paths(PathSelectors.any())
//                .build().apiInfo(ApiInfo.DEFAULT)
//    }


    /*
    https://github.com/springfox/springfox/issues/2360
     */
}