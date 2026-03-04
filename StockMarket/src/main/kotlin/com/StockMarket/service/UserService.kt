package com.StockMarket.service

import com.StockMarket.model.User

class UserService {
    lateinit var users: MutableMap<Int, User>

    fun register(first_name: String, last_name: String, email: String)
}