package dev.kourier.model

import java.time.LocalDateTime

sealed interface DatesAndPrices {
    data class DateTime(val dateTime: LocalDateTime)
    data class Price(val price: Float)
}

data class PriceHistory(val symbol: String,
                        var datesAndPrices: MutableList<DatesAndPrices>)