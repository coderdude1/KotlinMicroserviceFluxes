package com.dood.tdd.kotlinmicroservice.users.handlers

import com.dood.tdd.kotlinmicroservice.users.model.User
import com.dood.tdd.kotlinmicroservice.users.repository.UserRepository
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.BodyInserters.fromPublisher
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import java.time.Duration


class UserHandler(private val userRepository: UserRepository) {
    fun getAllUsers(request: ServerRequest) = ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(fromPublisher(userRepository.findAll(), User::class.java)) //vs fromObject.  these are BodyPublisher functions

    fun addUser(request: ServerRequest) = request.bodyToMono(User::class.java) //java class reference from example.  dig into this understand different types
            .flatMap(::saveAndRespond)

    fun initDbWithUsers() {

    }

    private fun saveAndRespond(user: User) = ServerResponse.ok() //figure out ResponseCode for created()
            .contentType(APPLICATION_JSON)
            .body(userRepository.save(user), User::class.java)
}