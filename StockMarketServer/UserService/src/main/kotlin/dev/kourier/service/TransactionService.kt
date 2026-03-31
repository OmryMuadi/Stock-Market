package dev.kourier.service

import dev.kourier.model.Transaction
import dev.kourier.model.TransactionType
import jdk.internal.agent.Agent.error
import org.springframework.stereotype.Service
//import repository.TransactionRepository
import java.time.LocalDateTime
import java.util.LinkedList
import java.util.UUID
import kotlin.math.PI


@Service
class TransactionService(private val portfolioService: PortfolioService,
                         private val latestPriceService: LatestPriceService) {

    val transactions: MutableMap<UUID, Transaction> = mutableMapOf()

    fun saveTransaction(type: TransactionType, userEmail: String, symbol: String, quantity: Int, currentPrice: Float) {
        val transaction: Transaction = Transaction(UUID.randomUUID(), type, userEmail,symbol,quantity, currentPrice, LocalDateTime.now())
        transactions.put(UUID.randomUUID(), transaction)
    }

    fun getTransactionsOfUser(userEmail: String): List<Transaction> {
        val userTransactions = LinkedList<Transaction>()
        for((key, value) in transactions){
            if (value.userEmail == userEmail) {
                userTransactions.add(value)
            }
        }
        return userTransactions
    }

    fun buyStock(userEmail: String, symbol: String, quantity: Int){
        try {
            val portfolio = portfolioService.getPortfolio(userEmail)
                ?: throw RuntimeException("Portfolio not found")

            if (latestPriceService.prices[symbol] == null) {
                throw IllegalArgumentException("Stock with symbol $symbol not found")
            }

            val currentPrice = latestPriceService.getLastPrice(symbol)

            val totalCost = quantity * currentPrice

            if (portfolio.cashBalance < totalCost) {
                throw RuntimeException("Insufficient funds")
            }

            portfolioService.decreaseCash(portfolio, totalCost)

            val existingHolding = portfolio.holdings.find { it.symbol == symbol }

            if (existingHolding != null) {
                val oldQty = existingHolding.quantity
                val oldAvg = existingHolding.averageBuyPrice

                val totalOldInvestment = oldQty * oldAvg
                val totalNewInvestment = quantity * currentPrice

                val newQuantity = oldQty + quantity
                val newAverageBuyPrice = (totalOldInvestment + totalNewInvestment) / newQuantity

                portfolioService.updateHolding(portfolio, symbol, newQuantity, newAverageBuyPrice, currentPrice)
            } else {
                portfolioService.addHolding(portfolio, symbol, quantity, currentPrice)

            }

            saveTransaction(TransactionType.BUY, userEmail, symbol, quantity, currentPrice)
            portfolioService.savePortfolio(portfolio)
        }
        catch (e: Exception){
            error(e.message)
        }
    }

    fun sellStock(userEmail: String, symbol: String, quantity: Int){
        try {
            val portfolio = portfolioService.getPortfolio(userEmail)
                ?: throw RuntimeException("Portfolio not found")

            if (latestPriceService.prices[symbol] == null) {
                throw IllegalArgumentException("Stock with symbol $symbol not found")
            }

            val existingHolding = portfolio.holdings.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("The user doesn't have stock with symbol $symbol")

            if (existingHolding.quantity < quantity) {
                throw IllegalArgumentException("The required quantity for selling is more than how much the user has")
            }

            val currentPrice = latestPriceService.getLastPrice(symbol)

            val totalCost = quantity * currentPrice

            portfolioService.increaseCash(portfolio, totalCost)

            val oldQty = existingHolding.quantity
            val oldAvg = existingHolding.averageBuyPrice

            existingHolding.quantity = existingHolding.quantity - quantity

            if (existingHolding.quantity == 0) {
                portfolioService.removeHolding(portfolio, symbol)
            } else {
                val totalOldInvestment = oldQty * oldAvg
                val totalNewInvestment = existingHolding.quantity * currentPrice

                val newQuantity = existingHolding.quantity
                val newAverageBuyPrice = (totalOldInvestment + totalNewInvestment) / newQuantity
                portfolioService.updateHolding(portfolio, symbol, newQuantity, newAverageBuyPrice, currentPrice)
            }
            saveTransaction(TransactionType.SELL, userEmail, symbol, quantity, currentPrice)
            portfolioService.savePortfolio(portfolio)
        }
        catch (e: Exception){
            error(e.message)
        }
    }

    fun deposit(userEmail: String,cash: Float){
        try {
            val userPortfolio = portfolioService.getPortfolio(userEmail)
                ?: throw IllegalArgumentException("User with email $userEmail doesn't have a portfolio")

            portfolioService.increaseCash(userPortfolio, cash)
        }
        catch (e: Exception){
            error(e.message)
        }
    }

    fun withdraw(userEmail: String,cash: Float){
        try {
            val userPortfolio = portfolioService.getPortfolio(userEmail)
                ?: throw IllegalArgumentException("User with email $userEmail doesn't have a portfolio")

            portfolioService.decreaseCash(userPortfolio, cash)
        }
        catch (e: Exception){
            error(e.message)
        }
    }
}