package com.dood.tdd.kotlinmicroservice.user

import groovyx.net.http.RESTClient
import spock.lang.Specification


class UserAcceptanceSpec extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080")

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
        def user = new User()

        when:
        def response = restClient.somethign

        then:
        response
        response.status == 200
        //I kknow there is a way to serailze this into a User
        response.data[0].id = user.id
    }
}