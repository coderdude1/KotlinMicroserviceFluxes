package com.dood.tdd.kotlinmicroservice.users

import org.springframework.web.reactive.function.server.ServerRequest

fun id(r: ServerRequest): String {
    return r.pathVariable("id")
}