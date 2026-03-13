package repository

import model.LatestPrice

class LatestPriceService {
    val prices: MutableMap<String ,LatestPrice> = emptyMap<String, LatestPrice>() as MutableMap<String, LatestPrice>

    fun save(latestPrice: LatestPrice){
        if (prices[latestPrice.symbol] != null){
            prices[latestPrice.symbol] = latestPrice
        }
        else{
            prices.put(latestPrice.symbol,latestPrice)
        }
    }

    fun getLastUpdate(symbol: String): LatestPrice? {
        return prices[symbol] ?: throw IllegalArgumentException("Stock with symbol $symbol not found")
    }
}