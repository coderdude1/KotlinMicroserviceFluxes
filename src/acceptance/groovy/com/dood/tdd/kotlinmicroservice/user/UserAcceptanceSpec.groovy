package com.dood.tdd.kotlinmicroservice.user

import com.dood.tdd.kotlinmicroservice.users.model.User
import groovyx.net.http.ContentType
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
        def user = new User('blarg', 'firstName', 'lastName')

        when:
//        def response = restClient.post(path : 'orders',
//                requestContentType : ContentType.JSON,
//                headers : ['Content-Type' : "application/json"],
//                body    : [ 'id'    :   user.id,
//                            'firstName' : user.firstName,
//                            'lastNAME' : user.lastName])

        def response

        then:
        response
        response.status == 200
        //I kknow there is a way to serailze this into a User
        response.data[0].id == user.id
        response.data[0].firstName == user.firstName
        response.data[0].lastName == user.lastName
    }
}