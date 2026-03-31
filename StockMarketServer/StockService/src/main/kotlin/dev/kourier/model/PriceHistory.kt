package dev.kourier.model

import java.time.LocalDateTime

data class PricePoint(
    val timestamp: LocalDateTime,
    val price: Float
)

data class PriceHistory(
    val symbol: String,
    var datesAndPrices: MutableList<PricePoint>)