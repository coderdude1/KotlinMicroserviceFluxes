package com.dood.tdd.kotlinmicroservice.users.web

import com.dood.tdd.kotlinmicroservice.users.model.User
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyExtractors.toFlux
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse


//todo add repository once mongo is wired in
class UserHandler {
    val results = listOf(User("id1", "fname1", "lname1"),
            User("id2", "fname2", "lname2"))

    fun getAllItems(request: ServerRequest) = ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(fromObject(results))
//fun getItem(request: ServerRequest) = null
    fun addItem(request: ServerRequest) = request.bodyToMono(User::class.java).flatMap(::saveAndRespond)

    private fun saveAndRespond(user: User) = ServerResponse.ok() //figure out URI for created()
            .contentType(APPLICATION_JSON)
            .body(fromObject(user))

//            .body(itemRepository.save(item), Item::class.java)
}