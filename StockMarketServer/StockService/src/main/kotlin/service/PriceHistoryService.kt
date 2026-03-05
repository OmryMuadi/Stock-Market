package dev.kourier.service

import dev.kourier.model.DatesAndPrices
import dev.kourier.model.PriceHistory

class PriceHistoryService {
    var priceHistories: MutableMap<String, PriceHistory>
    constructor(){
        priceHistories = emptyMap<String, PriceHistory>() as MutableMap<String, PriceHistory>
    }

    fun getPriceHistoryOfStock(symbol: String): MutableList<DatesAndPrices>{
        if (priceHistories[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol not found in history")
        }
        return priceHistories[symbol]!!.datesAndPrices
    }
}