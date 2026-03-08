package repository

import model.Portfolio
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PortfolioRepository : MongoRepository<Portfolio, Int> {
    fun findByUserId(userId: Int): Portfolio?
}