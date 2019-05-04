package com.dood.tdd.kotlinmicroservice.users.handlers

import com.dood.tdd.kotlinmicroservice.users.id
import com.dood.tdd.kotlinmicroservice.users.model.User
import com.dood.tdd.kotlinmicroservice.users.repository.UserRepository
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters.fromPublisher
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

/**
 * Still figuring out code boundries, ie the responsibilties of a handler vs a service.
 * For now will delegate to the Repository since this currently doesn't do much.  At some
 * point it may make sense to extract some/all of the biz logic into a userService and
 * allow the handler to mix and match service calls.  maybe....
 */
class UserHandler(private val userRepository: UserRepository) {
    fun getAllUsers(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(fromPublisher(userRepository.findAll(), User::class.java)) //vs fromObject.  these are BodyPublisher functions

    fun getUserById(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(userRepository.findById(id(request)), User::class.java)

    fun addUser(request: ServerRequest): Mono<ServerResponse> = request.bodyToMono(User::class.java) //java class reference from example.  dig into this understand different types
            .flatMap(::saveAndRespond)

    //returns a Mono<User>
    private fun saveAndRespond(user: User) = ServerResponse.ok() //figure out ResponseCode for created()
            .contentType(APPLICATION_JSON)
            .body(userRepository.save(user), User::class.java)
}
