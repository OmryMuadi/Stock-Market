package model

data class Holding(val symbol: String,
                   var quantity: Int,
                   var averageBuyPrice: Double)