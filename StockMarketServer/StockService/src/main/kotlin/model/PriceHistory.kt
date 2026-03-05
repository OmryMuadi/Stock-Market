package dev.kourier.model

data class PriceHistory(val symbol: String,
                        var datesAndPrices: MutableList<DatesAndPrices>)