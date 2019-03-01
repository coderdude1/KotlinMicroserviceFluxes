package com.dood.tdd.kotlinmicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = arrayOf(MongoReactiveDataAutoConfiguration::class))
class KotlinmicroserviceApplication

fun main(args: Array<String>) {
    runApplication<KotlinmicroserviceApplication>(*args)
}
