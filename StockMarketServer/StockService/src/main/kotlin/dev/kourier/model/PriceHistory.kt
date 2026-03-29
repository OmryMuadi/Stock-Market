package dev.kourier.model

import java.time.LocalDateTime

data class PricePoint(
    val timestamp: LocalDateTime,
    val price: Float
)

//@Document(collection = "price_history")
//data class PriceHistory(
//    @Id
//    val symbol: String,
//    var datesAndPrices: MutableList<PricePoint>)

data class PriceHistory(
    val symbol: String,
    var datesAndPrices: MutableList<PricePoint>)