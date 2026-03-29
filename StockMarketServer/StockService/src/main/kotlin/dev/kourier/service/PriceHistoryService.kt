package dev.kourier.service

import dev.kourier.model.PriceHistory
import dev.kourier.model.PricePoint
import org.springframework.stereotype.Service

@Service
class PriceHistoryService {
    var priceHistories: MutableMap<String, PriceHistory> = mutableMapOf()

    fun getPriceHistoryOfStock(symbol: String): MutableList<PricePoint>{
        if (priceHistories[symbol] == null){
            priceHistories[symbol] = PriceHistory(symbol, mutableListOf())
        }
        return priceHistories[symbol]!!.datesAndPrices
    }

    fun addStock(symbol: String){
        priceHistories.put(symbol, PriceHistory(symbol, mutableListOf()))
    }

    fun getEntityByStock(symbol: String): PriceHistory? {
        if (priceHistories[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol not found in history")
        }
        return priceHistories[symbol]
    }
}