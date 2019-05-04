package com.dood.tdd.kotlinmicroservice.users.web

import com.dood.tdd.kotlinmicroservice.users.handlers.UserHandler
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

/**
 * ATM it appears that these routers (alternate to controllers) accept a ServerREqeust
 * and return a Mono<ServerResponse>, thus really flux/mono's only.
 */
@ApiOperation(value="kotlin dsl router")
fun router(userHandler: UserHandler) = router {
    accept(APPLICATION_JSON).nest {
        "/api".nest { //commented out for kotlin and swagger test
            "/users".nest {
                GET("/", userHandler::getAllUsers)
            }
            "/user".nest {
                //                PUT("/{id}", userHandler::updateItem)
                GET("/{id}", userHandler::getUserById)
                POST("/", userHandler::addUser)
            }
        }
    }
}