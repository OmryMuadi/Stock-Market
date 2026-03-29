package com.UserService.model

import java.util.*

data class User(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    var isLoggedIn: Boolean)