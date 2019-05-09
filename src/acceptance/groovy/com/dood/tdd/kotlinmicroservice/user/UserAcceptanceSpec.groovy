package com.dood.tdd.kotlinmicroservice.user

import com.dood.tdd.kotlinmicroservice.users.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.client.WebClient
import spock.lang.Specification

import java.time.Duration

/**
 * acceptanceTesting using WebClient and WebTestClient to learn the capabilities of each.
 *
 */
class UserAcceptanceSpec extends Specification {
//alternate way to create the WebClient
//    WebClient webClient = WebClient.create("http://localhost:8080/api")

//    WebClient webClient = WebClient.create()
    WebClient webClient
    def port = 8081

//            WebClient.builder()
//            .baseUrl("http://localhost:8080/api/user/")
//            .build()

    WebTestClient webTestClient

    def setup() {
        //seeing this java.lang.UnsupportedOperationException: Reflective setAccessible(true) disabled
        //but still works
        webClient = WebClient.create("http://localhost:${port}/api/")

        webTestClient =  WebTestClient
                .bindToServer()
                .responseTimeout(Duration.ofMillis(30000)) //when using an Interval on sending side to slow it down
                .baseUrl("http://localhost:${port}/api")
                .build()

        //TODO add deleteAll endpoint, call it to start fresh on acc tests
        //TODO create testFixture to allow better acc testing
    }

    def 'add a new user with POST using a webClient'() {
        given:
        def user = new User(null, 'firstName', 'lastName')

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
        User retval = response.bodyToMono(User.class).block() //bad practice for normal code, ie don't block normally
//        retval == user
        retval.firstName == user.firstName
        retval.lastName == user.lastName
        retval.id != null
    }

    def 'get all users'() {
        given:
        //create a list of 5 users using the post endpoint.  Need to start with a clean db instance
        //so these aren't the best tests (ie this will need to call an init or delteAll endpoint,
        //or have the notion of a projectId or some such to segment testing data from non-testing
        //currently this test is very brittle, ie run the previous test again and this test fails due to userCounts
        expect:
        def results = webTestClient.get()
                .uri("/users")
                .exchange()
                    .expectStatus()
                        .isOk()
                    .expectHeader()
                        .contentType(MediaType.APPLICATION_JSON)
                    .expectBodyList(User.class)
                        .hasSize(2) //TODO need to have a way to bootstrap a empty collection as this varies then
                    .returnResult()

        def body = results.getResponseBody()
        body.size() == 2
        //check contesnt
    }

//    def 'get a user by id'() {
//        given:
//
//    }
}