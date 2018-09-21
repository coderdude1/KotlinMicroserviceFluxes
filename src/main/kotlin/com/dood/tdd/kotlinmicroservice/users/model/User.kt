package com.dood.tdd.kotlinmicroservice.users.model

//add @Id to id when mongofying it
data class User(val id: String?, val firstName: String, val lastName: String)