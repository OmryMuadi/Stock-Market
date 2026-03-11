package dev.kourier.service

import dev.kourier.model.PriceHistory
import dev.kourier.model.PricePoint

class PriceHistoryService {
    var priceHistories: MutableMap<String, PriceHistory>
    constructor(){
        priceHistories = emptyMap<String, PriceHistory>() as MutableMap<String, PriceHistory>
    }

    fun getPriceHistoryOfStock(symbol: String): MutableList<PricePoint>{
        if (priceHistories[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol not found in history")
        }
        return priceHistories[symbol]!!.datesAndPrices
    }

    fun getEntityByStock(symbol: String): PriceHistory? {
        if (priceHistories[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol not found in history")
        }
        return priceHistories[symbol]
    }
}