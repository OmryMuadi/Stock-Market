package dev.kourier.service

import dev.kourier.model.Stock
import java.time.LocalDateTime

class StockService {
    var stocks: MutableMap<String, Stock>;
    constructor(){
        stocks = emptyMap<String, Stock>() as MutableMap<String, Stock>
    }

    fun getStock(symbol: String): Stock{
        if(stocks[symbol] == null){
            throw IllegalArgumentException("Stock with symbol $symbol not found")
        }
        return stocks[symbol]!!
    }

    fun getAllStocks(): List<Stock>{
        return stocks.values.toList()
    }

    fun addStock(symbol: String, name: String, currentPrice: Float){
        stocks.put(symbol, Stock(symbol, name, currentPrice, LocalDateTime.now()))
    }
}