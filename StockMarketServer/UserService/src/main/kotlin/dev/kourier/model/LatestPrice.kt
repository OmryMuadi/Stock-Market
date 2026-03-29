package dev.kourier.model

import java.time.LocalDateTime

data class LatestPrice(
    val symbol: String,
    val price: Float,
    val timestamp: LocalDateTime
)
