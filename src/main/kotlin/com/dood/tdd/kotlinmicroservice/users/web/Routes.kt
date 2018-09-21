package com.dood.tdd.kotlinmicroservice.users.web

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

fun router(userHandler: UserHandler) = router {
    accept(APPLICATION_JSON).nest {
        "/api".nest {
            "/users".nest {
                                GET("/", userHandler::getAllItems)
            }
            "/user".nest {
                //                PUT("/{id}", userHandler::updateItem)
//                GET("/{id}", userHandler::getItem)
//                POST("/add", userHandler::addItem)
                POST("/", userHandler::addItem)
            }
        }
    }
}