package service

import controller.MarketDataAPI.MarketDataClient
import model.TransactionType
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TradingService(private val portfolioService: PortfolioService,
                     private val transactionService: TransactionService) {
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

        portfolioService.decreaseCash(portfolio, totalCost)

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

        transactionService.saveTransaction(TransactionType.BUY, userEmail, symbol, quantity, currentPrice)
        portfolioService.savePortfolio(portfolio)
    }

    fun sellStock(userEmail: String, symbol: String, quantity: Int){
        val portfolio = portfolioService.getPortfolio(userEmail)
            ?: throw RuntimeException("Portfolio not found")

        val stock = MarketDataClient.getStockBySymbol(symbol)
            ?: throw RuntimeException("Stock not found")

        val existingHolding = portfolio.holdings.find { it.symbol == symbol } ?:
        throw IllegalArgumentException("The user doesn't have stock with symbol $symbol")

        if (existingHolding.quantity < quantity){
            throw IllegalArgumentException("The required quantity for selling is more than how much the user has")
        }

        val currentPrice = stock.currentPrice

        val totalCost = quantity * currentPrice

        portfolioService.increaseCash(portfolio, totalCost)

        val oldQty = existingHolding.quantity
        val oldAvg = existingHolding.averageBuyPrice

        existingHolding.quantity = existingHolding.quantity - quantity

        if (existingHolding.quantity == 0){
            portfolioService.removeHolding(portfolio, symbol)
        }
        else{
            val totalOldInvestment = oldQty * oldAvg
            val totalNewInvestment = existingHolding.quantity * currentPrice

            val newQuantity = existingHolding.quantity
            val newAverageBuyPrice = (totalOldInvestment + totalNewInvestment) / newQuantity
            portfolioService.updateHolding(portfolio, symbol, newQuantity, newAverageBuyPrice)
        }
        transactionService.saveTransaction(TransactionType.SELL, userEmail, symbol, quantity, currentPrice)
        portfolioService.savePortfolio(portfolio)
    }
}