package com.UserService.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    @Id
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    var isLoggedIn: Boolean)