package com.dood.tdd.kotlinmicroservice.users.repository

import com.dood.tdd.kotlinmicroservice.users.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

//ReactiveMongoRepository allowes Query By Example vs ReactiveMongoCrudReoposityr
interface UserRepository : ReactiveMongoRepository<User, String>

/**
 * got this from the example I was looking at.  Its a good example of using the Spring Boot CommandLineRunner
 * to initiilizae the db using the flux
 */
//internal fun init(repository: UserRepository) = CommandLineRunner {
//    Flux.just(
//            User(null, "A", "aLastName"),
//            User(null, "B", "bLastName"),
//            User(null, "C", "cLastName"))
//            .flatMap { repository.save(it) } //TODO figure out why a flatMap is required, assume the flux returns multiple streams?
//            .thenMany(repository.findAll())
//            .subscribe { println(it) }
//}