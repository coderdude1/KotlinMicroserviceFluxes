package com.dood.tdd.kotlinmicroservice.user

import com.dood.tdd.kotlinmicroservice.users.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.client.WebClient
import spock.lang.Specification

import java.time.Duration

class UserAcceptanceSpec extends Specification {
//alternate way to create the WebClient
//    WebClient webClient = WebClient.create("http://localhost:8080/api")

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/api")
            .build()

    WebTestClient webTestClient

    def setup() {
        webTestClient =  WebTestClient
                .bindToServer()
                .responseTimeout(Duration.ofMillis(30000)) //when using an Interval on sending side to slow it down
                .baseUrl("http://localhost:8080/api")
                .build()
    }

    def 'add a new user with POST'() {
        given:
        def user = new User('blarg', 'firstName', 'lastName')

        when:
        def response = webClient.post()
                                .uri("/user/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .syncBody(user)
                                .exchange()
                                .block() //get the ClientResponse so we can check status

        then:
        response
        response.statusCode() == HttpStatus.OK //todo figure out 201
        User retval = response.bodyToMono(User.class).block()
        retval == user
    }

    def 'get all users'() {
        given:
        def blah = webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus()
                    .isOk()
                .expectHeader()
                    .contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(User.class)
                    .hasSize(5)
                .returnResult()

        expect:
        blah
        //could do some testing of the actual users that came back
    }

//    def 'get a user by id'() {
//        given:
//
//    }
}