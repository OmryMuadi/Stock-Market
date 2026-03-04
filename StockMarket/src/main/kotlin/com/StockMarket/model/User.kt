package com.StockMarket.model

data class User(val first_name: String, val last_name: String, val email: String,
                val password: String, var stock: List<Stock>, var cashBalance: Float)