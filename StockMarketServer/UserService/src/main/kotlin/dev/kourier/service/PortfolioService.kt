package dev.kourier.service

import dev.kourier.model.Holding
import dev.kourier.model.Portfolio
import org.springframework.stereotype.Service

//import repository.PortfolioRepository

@Service
class PortfolioService{
    val portfolios: MutableMap<String, Portfolio> = mutableMapOf()

    fun getPortfolio(userEmail: String): Portfolio? {
        return portfolios[userEmail] ?: throw IllegalArgumentException("Portfolio for user email $userEmail not found")
    }

    fun savePortfolio(portfolio: Portfolio) {
        portfolios.put(portfolio.userEmail, portfolio)
    }

    fun decreaseCash(portfolio: Portfolio,cash: Float){
        if (portfolio.cashBalance - cash < 0){
            throw IllegalArgumentException("You cannot withdraw money more than what you actually have")
        }
        portfolio.cashBalance = portfolio.cashBalance - cash
    }

    fun increaseCash(portfolio: Portfolio,cash: Float){
        portfolio.cashBalance = portfolio.cashBalance + cash
    }

    fun addHolding(portfolio: Portfolio, symbol: String, quantity: Int, currentPrice: Float){
        val holding: Holding = Holding(symbol, quantity, currentPrice, currentPrice)
        portfolio.holdings.add(holding)
    }

    fun updateHolding(portfolio: Portfolio, symbol: String, newQuantity: Int, newAveragePrice: Float, currentPrice: Float){
        val holding = portfolio.holdings.find { it.symbol == symbol }
        holding?.quantity = newQuantity
        holding?.averageBuyPrice = newAveragePrice
        holding?.currentPrice = currentPrice
    }

    fun removeHolding(portfolio: Portfolio, symbol: String): Boolean {
        return portfolio.holdings.removeIf { it.symbol == symbol }
    }

    fun totalValue(portfolio: Portfolio): Float{
        var total: Float = 0.0.toFloat()
        for (holding in portfolio.holdings){
            total = total + holding.averageBuyPrice * holding.quantity
        }
        return total
    }
}