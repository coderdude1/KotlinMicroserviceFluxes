package com.dood.tdd.kotlinmicroservice.users.web

import com.dood.tdd.kotlinmicroservice.users.handlers.UserHandler
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

fun router(userHandler: UserHandler) = router {
    accept(APPLICATION_JSON).nest {
        "/api".nest {
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
    }
}