package com.dood.tdd.kotlinmicroservice.users.config

import com.dood.tdd.kotlinmicroservice.users.repository.UserRepository
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

/**
 * THis stuff is needed to make mongo reactive.  Not needed (exccept for maybe getDatabaseName?) for normal mongo
 */
@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = arrayOf(UserRepository::class))
//@EnableMongoAuditing //TODO figure out what this is.  looks cool
class MongoReactiveConfig : AbstractReactiveMongoConfiguration() {

//    override fun reactiveMongoClient(): MongoClient = MongoClients.create()
    override fun reactiveMongoClient(): MongoClient = mongoClient()

    override fun getDatabaseName(): String = "reactiveKotlinExperiment" //TODO read from yml

    @Bean
    fun mongoClient() : MongoClient = MongoClients.create()

    @Bean
    override fun reactiveMongoTemplate() : ReactiveMongoTemplate
            = ReactiveMongoTemplate(mongoClient(), databaseName)
}