package service

import controller.MarketDataAPI.MarketDataClient
import org.springframework.stereotype.Service

@Service
class TradingService(val portfolioService: PortfolioService) {
    fun buyStock(userEmail: String, symbol: String, quantity: Int){
        val portfolio = portfolioService.getPortfolio(userEmail)
            ?: throw RuntimeException("Portfolio not found")

        val stock = MarketDataClient.getStockBySymbol(symbol)
            ?: throw RuntimeException("Stock not found")

        val currentPrice = stock.currentPrice

        val totalCost = quantity * currentPrice

        if (portfolio.cashBalance < totalCost) {
            throw RuntimeException("Insufficient funds")
        }

        portfolio.cashBalance = portfolio.cashBalance - totalCost

        val existingHolding = portfolio.holdings.find { it.symbol == symbol }

        if (existingHolding != null) {
            val oldQty = existingHolding.quantity
            val oldAvg = existingHolding.averageBuyPrice

            val totalOldInvestment = oldQty * oldAvg
            val totalNewInvestment = quantity * currentPrice

            val newQuantity = oldQty + quantity
            val newAverageBuyPrice = (totalOldInvestment + totalNewInvestment) / newQuantity

            portfolioService.updateHolding(portfolio, symbol, newQuantity, newAverageBuyPrice)
        } else {
            portfolioService.addHolding(portfolio, symbol, quantity, currentPrice)
        }
        // to be continued, I should add transaction service
    }

    fun sellStock(userEmail: String, symbol: String, quantity: Int){
        val portfolio = portfolioService.getPortfolio(userEmail)
            ?: throw RuntimeException("Portfolio not found")
        
    }
}