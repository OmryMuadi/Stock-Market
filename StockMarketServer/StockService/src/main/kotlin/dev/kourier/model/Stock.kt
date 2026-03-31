package dev.kourier.model

import java.time.LocalDateTime

data class Stock(
    val symbol: String,
    val name: String,
    var currentPrice: Float,
    var lastUpdatedAt: LocalDateTime
)