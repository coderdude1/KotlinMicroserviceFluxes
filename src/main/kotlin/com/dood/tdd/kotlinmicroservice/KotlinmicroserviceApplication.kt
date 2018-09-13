package com.dood.tdd.kotlinmicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinmicroserviceApplication

fun main(args: Array<String>) {
    runApplication<KotlinmicroserviceApplication>(*args)
}
