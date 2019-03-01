package com.dood.tdd.kotlinmicroservice.users.config

import com.dood.tdd.kotlinmicroservice.users.handlers.UserHandler
import com.dood.tdd.kotlinmicroservice.users.web.router
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

class Beans : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(context: GenericApplicationContext) = beans {
        bean { UserHandler(ref()) } //passes in the repository dep once I use that
        bean { router(ref()) }
//        bean { init(ref()) } //this is the repository init using the init CommandLineRunner
    }.initialize(context)
}