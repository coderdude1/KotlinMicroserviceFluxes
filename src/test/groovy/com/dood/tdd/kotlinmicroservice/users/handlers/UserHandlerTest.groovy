package com.dood.tdd.kotlinmicroservice.users.handlers

import com.dood.tdd.kotlinmicroservice.users.model.User
import com.dood.tdd.kotlinmicroservice.users.repository.UserRepository
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject

//TODO could not get the kotiln reactive extensions to work in a reasonable amount of time, skipping for now
//import reactor.core.publisher.toMono

class UserHandlerTest extends Specification {
    @Subject
    UserHandler userHandler
    UserRepository mockUserRepository = Mock() //using types vs def to get intellij type hints/avoid warnings

    def setup() {
        userHandler = new UserHandler(mockUserRepository)
    }

    def 'saving a new user does what its supposed to do' () {
        given:
        def user = new User('some id that mongo generates if this used mongo', 'fname', 'lname')

        when:
        Mono<User> createdUser = userHandler.saveAndRespond(user)

        then:
//        mockUserRepository.save(user) >> user.toMono()// could not get the kotlin extension to load, will look later
        1 * mockUserRepository.save(user) >> Mono.just(user) //the toMono is a reactor kotlin extension, need to verify
        createdUser
//        def returnedUser = createdUser.subscribe().
    }
}
