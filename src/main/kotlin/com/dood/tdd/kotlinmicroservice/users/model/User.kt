package com.dood.tdd.kotlinmicroservice.users.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(@Id val id: String?, val firstName: String, val lastName: String)