package com.UserService.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

//@Document(collection = "users")
//data class User(
//    @Id
//    val id: Int,
//    val firstName: String,
//    val lastName: String,
//    val email: String,
//    val password: String,
//    var isLoggedIn: Boolean)

data class User(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    var isLoggedIn: Boolean)