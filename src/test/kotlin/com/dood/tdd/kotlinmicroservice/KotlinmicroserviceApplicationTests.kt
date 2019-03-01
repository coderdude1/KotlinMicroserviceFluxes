package com.dood.tdd.kotlinmicroservice

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest //todo figure out how to do int tests with embedded mongo
class KotlinmicroserviceApplicationTests {

    @Test
    fun contextLoads() {
    }

}
