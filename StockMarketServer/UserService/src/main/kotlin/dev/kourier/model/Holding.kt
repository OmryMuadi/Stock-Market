package dev.kourier.model

data class Holding(val symbol: String,
                   var quantity: Int,
                   var averageBuyPrice: Float,
                   var currentPrice: Float
    )