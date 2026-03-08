package dev.kourier.repository

import dev.kourier.model.Stock
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : MongoRepository<Stock, String> {
    fun findBySymbol(symbol: String): Stock?
}