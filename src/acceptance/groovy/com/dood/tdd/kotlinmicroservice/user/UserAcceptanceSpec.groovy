package com.dood.tdd.kotlinmicroservice.user

import com.dood.tdd.kotlinmicroservice.users.model.User
import groovyx.net.http.RESTClient
import org.springframework.http.MediaType
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient
import spock.lang.Specification

class UserAcceptanceSpec extends Specification {

    //first attempt before switching to WebClient.  keeping it here for posterity
    RESTClient restClient = new RESTClient("http://localhost:8080")
    WebClient webClient = WebClient.create("http://localhost:8080/api")

    def setup() {
        restClient.handler.failure = { resp, data ->
            resp.setData(data)
            String headers = ''
            resp.headers.each { h ->
                headers = headers + "${h.name} : ${h.value}\n"
            }
            return resp
        }
    }

    def 'add a new user'() {
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
        response.statusCode() == HttpStatus.OK
        User retval = response.bodyToMono(User.class).block()
        retval == user
    }
}