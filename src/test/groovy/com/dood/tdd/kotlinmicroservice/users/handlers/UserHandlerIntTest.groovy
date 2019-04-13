package com.dood.tdd.kotlinmicroservice.users.handlers

import com.dood.tdd.kotlinmicroservice.users.model.User
import com.dood.tdd.kotlinmicroservice.users.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

import java.util.stream.Collectors

// https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/html/boot-features-testing.html
//@RunWith(SpringRunner.class) //This breaks stuff with spock
@DataMongoTest
class UserHandlerIntSpec extends Specification {
    @Autowired
    private MongoTemplate mongoTemplate
    @Autowired
    private UserRepository subject

    def setup() {
        println('setup called')
    }

    def cleanup() {
        println('cleanup called')
        mongoTemplate.dropCollection(User.class)
    }

    def 'simple test'() {
        given:
        def userOne = createUser('oneF', 'oneL')
        def userTwo = createUser('oneF', 'oneL')
        def userThree = createUser('oneF', 'oneL')
        def userFour = createUser('oneF', 'oneL')

        when:
        def results = subject.findAll()

        then:
        results != null
        List<User> users = results.toStream().collect(Collectors.toList()) //probably a better way to do this, still learning
        users.size() == 4
        users.contains(userOne)
        users.contains(userTwo)
        users.contains(userThree)
        users.contains(userFour)
    }

    private def createUser(def firstName, def lastName) {
        def user = new User(null, firstName, lastName)
        mongoTemplate.insert(user)
        user
    }
}
