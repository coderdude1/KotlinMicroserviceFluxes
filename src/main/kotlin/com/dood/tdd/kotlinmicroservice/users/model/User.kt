package com.dood.tdd.kotlinmicroservice.users.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(@Id var id: String?, val firstName: String, val lastName: String)