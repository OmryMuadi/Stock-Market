package dev.kourier.service

import dev.kourier.model.PriceHistory
import dev.kourier.model.PricePoint
import org.springframework.stereotype.Service

@Service
class PriceHistoryService {
    var priceHistories: MutableMap<String, PriceHistory> = mutableMapOf()

    fun addPricePoint(symbol: String, pricePoint: PricePoint){
        if (priceHistories[symbol] == null){
            priceHistories[symbol] = PriceHistory(symbol, mutableListOf(pricePoint))
        }
        priceHistories[symbol]!!.datesAndPrices.add(pricePoint)
    }

    fun getStockPriceHistory(symbol: String): MutableList<PricePoint> {
        if (priceHistories[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol not found in history")
        }
        return priceHistories[symbol]!!.datesAndPrices
    }
}