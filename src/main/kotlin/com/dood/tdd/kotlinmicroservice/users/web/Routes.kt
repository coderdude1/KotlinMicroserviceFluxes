package com.dood.tdd.kotlinmicroservice.users.web

import com.dood.tdd.kotlinmicroservice.users.handlers.UserHandler
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@ApiOperation(value="kotlin dsl router")
fun router(userHandler: UserHandler) = router {
    accept(APPLICATION_JSON).nest {
//        "/api".nest { //commented out for kotlin and swagger test
            "/users".nest {
                GET("/", userHandler::getAllUsers)
            }
            "/user".nest {
                //                PUT("/{id}", userHandler::updateItem)
//                GET("/{id}", userHandler::getItem)
//                POST("/add", userHandler::addUser)
                POST("/", userHandler::addUser)
            }
        }
//    } //commented "/api"
}