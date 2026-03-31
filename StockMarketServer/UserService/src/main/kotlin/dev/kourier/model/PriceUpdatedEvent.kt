package dev.kourier.model

import java.time.LocalDateTime

data class PriceUpdatedEvent(
    val symbol: String,
    val price: Float,
    val timestamp: LocalDateTime
)
