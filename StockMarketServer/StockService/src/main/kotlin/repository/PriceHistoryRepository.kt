package dev.kourier.repository

import dev.kourier.model.PriceHistory
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PriceHistoryRepository : MongoRepository<PriceHistory, String> {
    fun findBySymbol(symbol: String): PriceHistory?
}