package com.dood.tdd.kotlinmicroservice.users.web

import com.dood.tdd.kotlinmicroservice.users.model.User
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.BodyInserters.fromPublisher
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import java.time.Duration


//todo add repository once mongo is wired in
class UserHandler {
    private val userList = mutableListOf(User("id1", "fname1", "lname1"),
                                User("id2", "fname2", "lname2"),
                                User("id3", "fname3", "lname3"),
                                User("id4", "fname4", "lname4"),
                                User("id5", "fname5", "lname5"))

    private val delayedUserFlux = Flux.fromIterable(userList)
            .delayElements(Duration.ofSeconds(1))

    fun getAllUsers(request: ServerRequest) = ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(fromPublisher(delayedUserFlux, User::class.java)) //vs fromObject.  these are BodyPublisher functions

    fun addUser(request: ServerRequest) = request.bodyToMono(User::class.java) //java class reference from example.  dig into this understand different types
            .flatMap(::saveAndRespond)

    private fun saveAndRespond(user: User) = ServerResponse.ok() //figure out URI for created()
            .contentType(APPLICATION_JSON)
            .body(fromObject(user))

//            .body(itemRepository.save(item), Item::class.java)
}