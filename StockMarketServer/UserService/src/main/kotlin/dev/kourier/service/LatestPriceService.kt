package dev.kourier.service

import dev.kourier.logger.Logger
import dev.kourier.model.LatestPrice
import org.springframework.stereotype.Service

@Service
class LatestPriceService {
    val prices: MutableMap<String , LatestPrice> = mutableMapOf()

    fun save(latestPrice: LatestPrice){
        if (prices[latestPrice.symbol] != null){
            prices[latestPrice.symbol] = latestPrice
        }
        else{
            prices.put(latestPrice.symbol,latestPrice)
        }
        Logger.log(prices.toString())
    }

    fun getLastPrice(symbol: String): Float {
        if (prices[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol is not found")
        }
        return prices[symbol]!!.price
    }
}