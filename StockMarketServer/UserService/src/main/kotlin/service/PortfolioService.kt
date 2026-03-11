package service

import com.UserService.service.UserService
import model.Holding
import model.Portfolio
import org.springframework.stereotype.Service
import repository.PortfolioRepository

@Service
class PortfolioService(val portfolioRepository: PortfolioRepository,
                        val userService: UserService){
    fun getPortfolio(userEmail: String): Portfolio? {
        return portfolioRepository.findByUserEmail(userEmail)
    }

    fun decreaseCash(portfolio: Portfolio,cash: Float){
        portfolio.cashBalance = portfolio.cashBalance - cash
    }

    fun increaseCash(portfolio: Portfolio,cash: Float){
        portfolio.cashBalance = portfolio.cashBalance + cash
    }

    fun addHolding(portfolio: Portfolio, symbol: String, quantity: Int, averagePrice: Float){
        val holding: Holding = Holding(symbol,quantity,averagePrice, false)
        portfolio.holdings.add(holding)
    }

    fun updateHolding(portfolio: Portfolio, symbol: String, newQuantity: Int, newAveragePrice: Float){
        val holding = portfolio.holdings.find { it.symbol == symbol }
        holding?.quantity = newQuantity
        holding?.averageBuyPrice = newAveragePrice
    }

    fun removeHolding(portfolio: Portfolio, symbol: String){
        portfolio.holdings.removeIf { it.symbol == symbol }
    }
}