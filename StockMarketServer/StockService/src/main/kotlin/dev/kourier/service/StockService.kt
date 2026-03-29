package dev.kourier.service

import dev.kourier.model.Stock
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StockService {
    var stocks: MutableMap<String, Stock> = mutableMapOf()

    constructor(){
        stocks.put("APPL", Stock("APPL","Apple", 50.toFloat(), LocalDateTime.now()))
    }

    fun getStock(symbol: String): Stock{
        if(stocks[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol not found")
        }
        return stocks[symbol]!!
    }

    fun addStock(symbol: String, name: String, currentPrice: Float){
        stocks.put(symbol, Stock(symbol, name, currentPrice, LocalDateTime.now()))
    }

//    fun updateStock(symbol: String, )

    fun getAllStocks(): List<Stock>{
        return stocks.values.toList()
    }
}