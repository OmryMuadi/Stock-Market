package dev.kourier.model

data class Stock(val id: Int, val name: String, val symbol: String,
                 val currentPrice: Float, val isFavorite: Boolean)